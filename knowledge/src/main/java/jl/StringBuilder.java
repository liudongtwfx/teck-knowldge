package jl;

public class StringBuilder {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 20; i++) {
            new Thread(new A()).start();
        }
    }

    private static class A implements Runnable {

        private volatile static int count;

        @Override
        public void run() {
            int ans = incrementAndGet();
            System.out.println(ans);
        }

        private int incrementAndGet() {
            for (int i = 0; i < 10000000; i++) {
                synchronized (A.class) {
                    count++;
                }
            }
            return count;
        }
    }
}
