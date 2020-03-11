package jl.gc;

/**
 * finalize垃圾回收时的执行动作
 */
public class Finalizer {
    public static void main(String[] args) {
        test();
        System.gc();
    }

    static void test() {
        A a = new A();
    }

    private static class A {
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("method invoke");
        }
    }
}

