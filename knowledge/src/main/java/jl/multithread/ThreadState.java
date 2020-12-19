package jl.multithread;

public class ThreadState {
    public static void main(String[] args) {
        new Thread(new NumberPrinter()).start();
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
}
