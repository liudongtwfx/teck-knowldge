package net.nio;


import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

@Slf4j
public class NioClient {
    private static final String HOST = "127.0.0.1";

    private static final int PORT = 8080;

    public static void main(String[] args) throws Exception {
        new FileOutputStream("").getChannel();
        for (int i = 0; i < 1; i++) {
            final int index = i;
            new Thread(() -> {
                try {
                    SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
                    socketChannel.configureBlocking(false);
                    socketChannel.write(ByteBuffer.wrap(String.format("hello world %s!", index).getBytes()));
                    Selector selector = Selector.open();
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    new Thread(new Response(selector)).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static class Response implements Runnable {
        private Selector selector;

        Response(Selector selector) {
            this.selector = selector;
        }

        @Override
        public void run() {
            int selectNum;
            while (true) {
                try {
                    if ((selectNum = selector.select()) > 0) {
                        log.info("current select num:{}", selectNum);
                        for (SelectionKey selectedKey : selector.selectedKeys()) {
                            if (selectedKey.isReadable()) {
                                processRead(selectedKey, (SocketChannel) selectedKey.channel());
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void processRead(SelectionKey selectionKey, SocketChannel channel) throws Exception {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (true) {
                int flag = channel.read(byteBuffer);
                if (flag == -1) {
                    log.info("服务端返回关闭");
                    return;
                }
                if (flag == 0) {
                    log.info("服务端数据读取完成");
                    break;
                }
                byteBuffer.flip();
                log.info("服务端数据返回:{}", Charset.defaultCharset().decode(byteBuffer).toString());
                byteBuffer.clear();
            }
            channel.register(selectionKey.selector(), SelectionKey.OP_READ);
        }
    }
}
