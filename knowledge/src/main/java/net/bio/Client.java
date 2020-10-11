package net.bio;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new RequestRunner(i)).start();
        }
    }

    private static class RequestRunner implements Runnable {
        private final int index;

        RequestRunner(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            try {
                //1.创建客户端Socket，指定服务器地址和端口
                Socket socket = new Socket("localhost", 8080);
                //2.获取输出流，向服务器端发送信息
                OutputStream os = socket.getOutputStream();//字节输出流
                PrintWriter pw = new PrintWriter(os);//将输出流包装为打印流
                pw.write("hello" + index);
                pw.flush();
                socket.shutdownOutput(); //关闭输出流
                //3.获取输入流，并读取服务器端的响应信息
                long start = System.currentTimeMillis();
                InputStream is = socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String info = null;
                while ((info = br.readLine()) != null) {
                    System.out.println("我是客户端" + index + "，服务器说：" + info);
                    System.out.println(index + "耗时" + (System.currentTimeMillis() - start));
                }
                //4.关闭资源
                br.close();
                is.close();
                pw.close();
                os.close();
                socket.close();
            } catch (IOException e) {
                System.out.println(index + "错误");
                e.printStackTrace();
            }
        }
    }
}
