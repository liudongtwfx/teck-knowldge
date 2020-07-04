package jl.multithread;

import java.util.Random;
import java.util.concurrent.atomic.LongAdder;

public class VolatileDemo {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 1000; i++) {
            new Thread(new Demo()).start();
        }

        Thread.sleep(1000);
        System.out.println(Demo.count.sum());
    }

    private static class Demo implements Runnable {
        private static final LongAdder count = new LongAdder();

        @Override
        public void run() {
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count.increment();
        }
    }
}
