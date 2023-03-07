package net.bio;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import java.util.concurrent.TimeUnit;

@Slf4j
public class BlockingIoServer {
    public static void main(String[] args) {
        new Thread(new BioRunner()).start();
    }


    private static class BioRunner implements Runnable {
        private ServerSocket serverSocket;

        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(8080);
                //使用多线程实现
                Executor executor = new ThreadPoolExecutor(10, 10, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024));
                while (true) {
                    Socket socket = serverSocket.accept();
                    RequestHandler requestHandler = new RequestHandler(socket);
                    /*requestHandler.start();*/
                    executor.execute(requestHandler);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (serverSocket != null) {
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class RequestHandler extends Thread {
        private final Socket socket;

        public RequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                InputStream inputStream = socket.getInputStream();
                log.info(StreamUtils.copyToString(inputStream, Charset.defaultCharset()));
                Thread.sleep(1000);
                out.println("Hello World");
                out.flush();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
