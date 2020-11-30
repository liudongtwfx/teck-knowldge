package jl.multithread;

import java.util.Random;

public class VolatileDemo {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 1000; i++) {
            new Thread(new Demo()).start();
        }

        Thread.sleep(5000);
        System.out.println(Demo.count);
    }

    private static class Demo implements Runnable {
        private static Integer count = 0;

        private static final Object LOCK = new Object();

        @Override
        public void run() {
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (LOCK) {
                count++;
            }
        }
    }
}
