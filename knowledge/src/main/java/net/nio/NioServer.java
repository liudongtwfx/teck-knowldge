package net.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class NioServer {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        try {
            // bossSelector负责轮询是否有新的连接，服务端监测到新的连接之后，不再创建一个新的线程，而是直接将新连接直接绑定到workerSelector上
            Selector bossSelector = Selector.open();
            // workerSelector负责轮询连接是否有数据可读
            Selector workerSelector = Selector.open();
            // 处理新的 socket 连接
            new Thread(new BossHandler(bossSelector, workerSelector, PORT)).start();
            // 处理 socket 连接的读写等处理
            new Thread(new WorkerHandler(workerSelector)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class BossHandler implements Runnable {
        private final Selector bossSelector;
        private final Selector workerSelector;

        BossHandler(Selector bossSelector, Selector workerSelector, int port) {
            this.bossSelector = bossSelector;
            this.workerSelector = workerSelector;
            try {
                // 服务器启动
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                // 服务器通道绑定8080端口
                serverSocketChannel.socket().bind(new InetSocketAddress(port));
                // 设置为非阻塞态，否则添加到selector中会报错
                serverSocketChannel.configureBlocking(false);
                // 注册服务器通道到bossSelector上，并监听SelectionKey.OP_ACCEPT事件
                serverSocketChannel.register(bossSelector, SelectionKey.OP_ACCEPT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (true) {
                try {
                    // 阻塞,直到有新的客户端连接请求
                    if (bossSelector.select() > 0) {
                        // 获取新的连接事件集合
                        Set<SelectionKey> selectionKeys = bossSelector.selectedKeys();
                        Iterator<SelectionKey> iterator = selectionKeys.iterator();
                        // 遍历新的连接事件集合
                        while (iterator.hasNext()) {
                            SelectionKey key = iterator.next();
                            // 这里只处理 SelectionKey.OP_ACCEPT事件
                            if (key.isAcceptable()) {
                                try {
                                    // 获取客户端请求通道
                                    SocketChannel workerChannel = ((ServerSocketChannel) key.channel()).accept();
                                    // 通道设置为非阻塞态
                                    workerChannel.configureBlocking(false);
                                    // 添加到workerSelector中，workerSelector选择器处理和客户端的数据交互(读写等)
                                    workerChannel.register(workerSelector, SelectionKey.OP_READ);
                                } finally {
                                    // [注意] 已经处理的事件一定要手动移除，否则会出现异常
                                    iterator.remove();
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Slf4j
    private static class WorkerHandler implements Runnable {

        private static final int CAPACITY = 1024;
        private final Selector workerSelector;

        public WorkerHandler(Selector workerSelector) {
            this.workerSelector = workerSelector;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    // 阻塞1ms等待新的触发事件
                    // [注意]这里不能用select(),否则服务端启动后，select()处于阻塞状态，且占有publicKeys锁，必须要通过register注册新的事件才能激活
                    // 但register方法需获取publicKeys锁，造成死锁
                    if (workerSelector.select(1) > 0) {
                        // 获取新的读写等事件集合
                        Set<SelectionKey> selectionKeys = workerSelector.selectedKeys();
                        Iterator<SelectionKey> iterator = selectionKeys.iterator();
                        while (iterator.hasNext()) {
                            // 获取客户端请求通道
                            SelectionKey key = iterator.next();
                            SocketChannel channel = (SocketChannel) key.channel();
                            if (key.isReadable()) {
                                try {
                                    // 处理读请求，【注意】Java NIO未单独定义客户端关闭请求，客户端关闭也会发送读请求
                                    doReadOrClose(channel, key);
                                } finally {
                                    // [注意] 已经处理的事件一定要手动移除，否则会出现异常
                                    iterator.remove();
                                }
                            } else if (key.isWritable()) {
                                try {
                                    // 处理写请求
                                    doWrite(channel);
                                } finally {
                                    // [注意] 已经处理的事件一定要手动移除，否则会出现异常
                                    iterator.remove();
                                    // 【注意】写完后，一定要记得将OP_WRITE事件注销，否则会导致select.select()不能正常阻塞，系统空转
                                    key.interestOps(key.interestOps() & ~SelectionKey.OP_WRITE);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void doWrite(SocketChannel channel) throws Exception {
            try {
                List<Integer> list = new ArrayList<>();
                System.out.println(list.get(1));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            String content = "Hello world";
            log.info("channel {} 向客户端回写", channel.getClass().getName());
            ByteBuffer byteBuffer = ByteBuffer.wrap(content.getBytes(), 0, content.length());
            int flag = channel.write(byteBuffer);
            byteBuffer.flip();
            log.info("flag:{},{}", flag, Charset.defaultCharset().newDecoder().decode(byteBuffer));
            if (flag == -1) {
                System.out.println("客户端写关闭");
                return;
            }
            // 如果flag=0,表示已读完数据
            if (flag == 0) {
                System.out.println("写数据完成");
                return;
            }
            byteBuffer.flip();
            Thread.sleep(1);
            byteBuffer.clear();
            channel.register(workerSelector, SelectionKey.OP_WRITE);
        }

        private void doReadOrClose(SocketChannel readChannel, SelectionKey key) throws Exception {
            ByteBuffer byteBuffer = ByteBuffer.allocate(CAPACITY);
            while (true) {
                int flag = readChannel.read(byteBuffer);
                if (flag == -1) {
                    System.out.println("客户端关闭");
                    // 客户端已断开连接,服务器也断开对应的通道
                    key.cancel();
                    readChannel.close();
                    return;
                }
                // 如果flag=0,表示已读完数据
                if (flag == 0) {
                    System.out.println("读取数据完成");
                    break;
                }
                byteBuffer.flip();
                log.info(Charset.defaultCharset().newDecoder().decode(byteBuffer).toString());
                Thread.sleep(1);
                byteBuffer.clear();
            }
            readChannel.register(workerSelector, SelectionKey.OP_WRITE);
        }
    }
}
