package jl.multithread;

import lombok.SneakyThrows;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadState {
    public static void main(String[] args) {
        new Thread(new NumberPrinter()).start();
        new Thread(new LocalThread(), "localThread1").start();
        new Thread(new LocalThread(), "localThread2").start();
        new Thread(new WaitThread(), "localThread3").start();
        new Thread(new WaitThread(), "localThread4").start();
        new Thread(new SleepThread(), "localThread5").start();
        new Thread(new BlockThread(), "localThread6").start();
        new Thread(new BlockThread(), "localThread7").start();
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
        private static final Object LOCK = new Object();

        @SneakyThrows
        @Override
        public void run() {
            synchronized (LOCK) {
                System.out.println(LOCK.hashCode());
                LOCK.wait();
                System.out.println("hello world");
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

    private static class BlockThread implements Runnable {
        private static final Object LOCK = new Object();

        @SneakyThrows
        @Override
        public void run() {
            synchronized (LOCK) {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(10000000);
            }
        }
    }
}
