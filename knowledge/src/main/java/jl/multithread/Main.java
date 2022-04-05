package jl.multithread;

public class Main {
    private static Integer NUM = 1;

    public static void main(String[] args) throws Exception {
        while (NUM < 5) {
            synchronized (NUM) {
                System.out.println(NUM);
                // NUM++;
                NUM.wait();
            }
        }
    }

    private static class Computer implements Runnable {
        @Override
        public void run() {
            long sum = 0;
            for (long i = 0; i < 10000000000L; i++) {
                sum += i;
            }
            System.out.println(sum);
        }
    }
}
