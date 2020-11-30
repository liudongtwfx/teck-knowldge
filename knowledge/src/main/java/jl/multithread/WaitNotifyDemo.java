package jl.multithread;

import lombok.SneakyThrows;

public class WaitNotifyDemo {
    private static final Object NUMBER_LOCK = new Object();
    private static final Object CHAR_LOCK = new Object();

    /**
     * 12a34b56e
     *
     * @param args
     */
    public static void main(String[] args) {
        new Thread(new NumberRunner()).start();
        new Thread(new CharRunner()).start();
    }

    public static class NumberRunner implements Runnable {
        private static int num = 0;

        @SneakyThrows
        @Override
        public void run() {
            synchronized (CHAR_LOCK) {
                while (true) {
                    // CHAR_LOCK.wait();
                    System.out.println(num++);
                    System.out.println(num++);
                    CHAR_LOCK.notify();
                    CHAR_LOCK.wait();
                }
            }
        }
    }

    public static class CharRunner implements Runnable {
        private static int num = 0;

        @Override
        @SneakyThrows
        public void run() {
            synchronized (CHAR_LOCK) {
                while (true) {
                    char c = (char) ('a' + num);
                    System.out.println(c);
                    if (c == 'z') {
                        System.exit(0);
                    }
                    num++;
                    CHAR_LOCK.notify();
                    CHAR_LOCK.wait();
                }
            }
        }
    }
}
