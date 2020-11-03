package net.nio.netty;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liudong
 */
@Log4j2
public class NettyServer {
    static final boolean SSL = System.getProperty("ssl") != null;
    static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));

    public static void main(String[] args) throws Exception {
        // Configure SSL.
        System.out.println(System.getProperty("ssl"));
        List<String> strs = new ArrayList<>();
        final SslContext sslCtx;
        if (SSL) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        } else {
            sslCtx = null;
        }


        // Configure the server.
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(10);
        final EchoServerHandler serverHandler = new EchoServerHandler();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            if (sslCtx != null) {
                                p.addLast(sslCtx.newHandler(ch.alloc()));
                            }
                            //p.addLast(new LoggingHandler(LogLevel.INFO));
                            p.addLast(serverHandler);
                        }
                    });

            // Start the server.
            ChannelFuture f = b.bind(PORT).sync();

            // Wait until the server socket is closed.
            f.channel().closeFuture().sync();
        } finally {
            // Shut down all event loops to terminate all threads.
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    @Sharable
    private static class EchoServerHandler extends ChannelInboundHandlerAdapter {
        private static final Map<String, String> CHANNEL_ID_MAP = new ConcurrentHashMap<>();

        EnhancedRandom random = new EnhancedRandomBuilder().stringLengthRange(1, 2).build();

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            Channel channel = ctx.channel();
            log.info("id is :{}", channel.id());
            ByteBuf result = (ByteBuf) msg;
            byte[] bytesMsg = new byte[result.readableBytes()];
            // msg中存储的是ByteBuf类型的数据，把数据读取到byte[]中
            result.readBytes(bytesMsg);
            String resultStr = new String(bytesMsg);

            System.out.println(resultStr);
            String clientId = resultStr.substring("hello world".length());
            CHANNEL_ID_MAP.put(channel.id().toString(), clientId);
            // 向客户端发送消息
            String response = "hello client!" + clientId + getRandomStr();
            // 在当前场景下，发送的数据必须转换成ByteBuf数组
            ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
            encoded.writeBytes(response.getBytes());
            ctx.writeAndFlush(encoded);
            printMap();
        }

        @Override
        public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
            //System.out.println("handler added");
        }

        @Override
        public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
            //System.out.println("handler removed");
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }


        public void printMap() {
            System.out.println(CHANNEL_ID_MAP.size());
            //CHANNEL_ID_MAP.forEach((channelId, clientId) -> System.out.println(channelId + " " + clientId));
        }

        private String getRandomStr() {
            return random.nextObject(String.class);
        }
    }
}
