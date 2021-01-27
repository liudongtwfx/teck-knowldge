package jl.lock;

import java.util.concurrent.locks.ReentrantLock;

public class AQSAnalysis {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Task()).start();
        }
    }

    static class Task implements Runnable {
        private static final ReentrantLock LOCK = new ReentrantLock(false);
        private final int index = 0;

        @Override
        public void run() {
            try {
                LOCK.lockInterruptibly();
                LOCK.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
