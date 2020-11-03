package net.nio.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class SimpleNettyClient {

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100; i++) {
            new Thread(new ClientRequest("localhost", 8007, i)).start();
        }
    }


    private static class ClientRequest implements Runnable {
        private final String host;
        private final int port;
        private int count;

        ClientRequest(String host, int port, int index) {
            this.host = host;
            this.port = port;
            this.count = index;
        }

        @Override
        public void run() {
            try {
                connect(host, port);

            } catch (Exception e) {
                log.error("exception", e);
            }
        }
    }

    public static void connect(String host, int port) throws Exception {
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            // 客户端启动类程序
            Bootstrap bootstrap = new Bootstrap();
            /**
             *EventLoop的组
             */
            bootstrap.group(worker);
            /**
             * 用于构造socketchannel工厂
             */
            bootstrap.channel(NioSocketChannel.class);
            /**设置选项
             * 参数：Socket的标准参数（key，value），可自行百度
             保持呼吸，不要断气！
             * */
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            /**
             * 自定义客户端Handle（客户端在这里搞事情）
             */
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    // ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
                    ch.pipeline().addLast(new SimpleNettyClientHandler());
                }
            });

            /** 开启客户端监听，连接到远程节点，阻塞等待直到连接完成*/
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            /**阻塞等待数据，直到channel关闭(客户端关闭)*/
            channelFuture.channel().closeFuture().sync();
        } finally {
            worker.shutdownGracefully();
        }
    }

    public static class SimpleNettyClientHandler extends ChannelInboundHandlerAdapter {
        private static final AtomicInteger INDEX = new AtomicInteger();

        /**
         * 本方法用于接收服务端发送过来的消息
         *
         * @param ctx
         * @param msg
         * @throws Exception
         */
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("SimpleClientHandler.channelRead");
            ByteBuf result = (ByteBuf) msg;
            byte[] result1 = new byte[result.readableBytes()];
            result.readBytes(result1);
            System.out.println("Server said:" + new String(result1));
            result.release();
        }

        /**
         * 本方法用于处理异常
         *
         * @param ctx
         * @param cause
         * @throws Exception
         */
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            // 当出现异常就关闭连接
            cause.printStackTrace();
            ctx.close();
        }

        /**
         * 本方法用于向服务端发送信息
         *
         * @param ctx
         * @throws Exception
         */
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            String msg = "hello world" + INDEX.getAndIncrement();
            ByteBuf encoded = ctx.alloc().buffer(4 * msg.length());
            encoded.writeBytes(msg.getBytes());
            ctx.write(encoded);
            ctx.flush();
        }
    }
}