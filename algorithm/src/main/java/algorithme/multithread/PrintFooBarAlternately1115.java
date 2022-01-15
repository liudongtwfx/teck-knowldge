package algorithme.multithread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintFooBarAlternately1115 {
    public static void main(String[] args) throws Exception {
        FooBar fooBar = new FooBar(10);
        CompletableFuture<Void> a = CompletableFuture.runAsync(() -> {
            try {
                fooBar.foo(() -> System.out.println("foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        CompletableFuture<Void> b = CompletableFuture.runAsync(() -> {
            try {
                fooBar.bar(() -> System.out.println("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        CompletableFuture<Void> all = CompletableFuture.allOf(a, b);
        all.get();
    }

    static class FooBar {
        private final Condition fooCondition;
        private final Condition barCondition;
        private final int n;
        private final ReentrantLock LOCK = new ReentrantLock();
        private volatile boolean barFirstPrint = false;

        public FooBar(int n) {
            this.n = n;
            fooCondition = LOCK.newCondition();
            barCondition = LOCK.newCondition();
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                LOCK.lockInterruptibly();
                try {
                    // printFoo.run() outputs "foo". Do not change or remove this line.
                    printFoo.run();
                    barFirstPrint = true;
                    barCondition.signal();
                    if (i == n - 1) {
                        return;
                    }
                    fooCondition.await();
                } finally {
                    LOCK.unlock();
                }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            while (!barFirstPrint) {
            }
            for (int i = 0; i < n; i++) {
                LOCK.lockInterruptibly();
                try {
                    // printBar.run() outputs "bar". Do not change or remove this line.
                    printBar.run();
                    fooCondition.signal();
                    if (i == n - 1) {
                        return;
                    }
                    barCondition.await();
                } finally {
                    LOCK.unlock();
                }
            }
        }
    }
}
