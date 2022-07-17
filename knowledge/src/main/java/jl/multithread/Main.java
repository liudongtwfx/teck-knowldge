package jl.multithread;


public class Main {

    private static Integer NUM = 1;

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        for (long i = 0; i < 10000000000L; i++) {
            long a = i % 21;
        }
        System.out.println("cost:{}" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        for (long i = 0; i < 10000000000L; i++) {
            long a = i & 15;
        }
        System.out.println("cost:{}" + (System.currentTimeMillis() - start));
    }

    private static class Pad {
        protected long p1, p2, p3, p4;
    }

    private static class ValueObject {
        protected long value;
    }
}
