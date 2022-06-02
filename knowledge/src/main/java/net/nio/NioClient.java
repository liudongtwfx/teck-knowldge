package net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClient {
    private static final String HOST = "127.0.0.1";

    private static final int PORT = 8080;

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            final int index = i;
            new Thread(() -> {
                try {
                    SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
                    socketChannel.write(ByteBuffer.wrap(String.format("hello world %s!", index).getBytes()));
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
