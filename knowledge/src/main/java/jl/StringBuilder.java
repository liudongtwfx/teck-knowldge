package jl;

public class StringBuilder {
    public static void main(String[] args) throws Exception {
    }

    private static class Task implements Runnable {
        private static final ThreadLocal<BigMemory> threadLocal = new ThreadLocal<BigMemory>();

        @Override
        public void run() {
            threadLocal.set(new BigMemory());
        }
    }

    private static class BigMemory {
        private int[] a = new int[1024 * 1024 * 400];

        @Override
        protected void finalize() throws Throwable {
            System.out.println("finalizing...");
            super.finalize();
        }
    }
}
