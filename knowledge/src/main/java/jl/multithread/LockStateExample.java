package jl.multithread;

public class LockStateExample {
    public static void main(String[] args) {
        lightWeight();
    }

    private static void lightWeight() {
        new Thread(new LightExampleRunner()).start();
        new Thread(new LightExampleRunner()).start();
    }

    static class LightExampleRunner implements Runnable {
        private static final Object LOCK = new Object();
        static int count = 0;

        @Override
        public void run() {
            synchronized (LOCK) {
                while (true) {
                    count++;
                }
            }
        }
    }
}
