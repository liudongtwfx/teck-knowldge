package jl.multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class FairLockExample {
    private static final CountDownLatch downLatch = new CountDownLatch(20);

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[20];
        for (int i = 0; i < 20; i++) {
            threads[i] = new Thread(new FairLockRunner(i));
        }
        for (int i = 0; i < 20; i++) {
            threads[i].start();
        }

        downLatch.await(10, TimeUnit.SECONDS);
        System.out.println("------start------");
        for (int i = 0; i < 20; i++) {
            threads[i] = new Thread(new NonFairLockRunner(i));
        }

        for (int i = 0; i < 20; i++) {
            threads[i].start();
        }
        System.out.println("hello world");
    }


    private static class FairLockRunner implements Runnable {
        private static final ReentrantLock LOCK = new ReentrantLock(true);

        private final int index;

        public FairLockRunner(int index) {
            this.index = index;
        }

        @Override
        public void run() {

            try {
                LOCK.lock();
                System.out.println("index:" + index);
            } catch (Exception e) {
            } finally {
                LOCK.unlock();
                downLatch.countDown();
            }
        }
    }


    private static class NonFairLockRunner implements Runnable {
        private static final ReentrantLock LOCK = new ReentrantLock(false);
        private final int index;

        public NonFairLockRunner(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            try {
                LOCK.lock();
                System.out.println("non_index:" + index);
            } catch (Exception e) {
            } finally {
                LOCK.unlock();
            }
        }
    }
}
