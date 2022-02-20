package jl.multithread;

import lombok.SneakyThrows;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadState {
    public static void main(String[] args) {
        // new Thread(new NumberPrinter()).start();
//        new Thread(new LocalThread(), "localThread1").start();
//        new Thread(new LocalThread(), "localThread2").start();
//        new Thread(new WaitThread(), "localThread3").start();
//        new Thread(new SleepThread(), "localThread4").start();
        System.getProperties().forEach((k, v) -> {
            String kStr = (String) k;
            if (kStr.contains("thread")) {
            }
            System.out.println("key:" + k + ",value:" + v);
        });
    }

    private static class NumberPrinter implements Runnable {

        @Override
        public void run() {
            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }

            System.out.println("ending");
        }
    }

    private static class LocalThread implements Runnable {

        private static final ReentrantLock lock = new ReentrantLock();

        @Override
        public void run() {
            lock.lock();
        }
    }

    private static class WaitThread implements Runnable {
        private static final Object lock = new Object();

        @SneakyThrows
        @Override
        public void run() {
            synchronized (lock) {
                lock.wait();
            }
        }
    }

    private static class SleepThread implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            Thread.sleep(10000000);
        }
    }
}
