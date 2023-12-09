package jl.multithread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LeetCodePrintInOrder1114 {
    public static void main(String[] args) {
        Foo foo = new Foo1();
        new Thread(() -> {
            try {
                foo.first(() -> System.out.print("first"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                foo.second(() -> System.out.print("second"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                foo.third(() -> System.out.print("third"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    static class Foo {
        private static final AtomicInteger COUNT = new AtomicInteger();

        public Foo() {

        }

        public void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            COUNT.incrementAndGet();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            while (COUNT.get() != 1) {
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            COUNT.incrementAndGet();
        }

        public void third(Runnable printThird) throws InterruptedException {
            while (COUNT.get() != 2) {
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
            COUNT.incrementAndGet();
        }
    }


    static class Foo1 extends Foo {
        private final Condition[] conditions;
        ReentrantLock lock = new ReentrantLock();
        private int value;

        public Foo1() {
            conditions = new Condition[3];
            for (int i = 0; i < 3; i++) {
                conditions[i] = lock.newCondition();
            }
        }

        public void first(Runnable printFirst) throws InterruptedException {
            lock.lock();
            // printFirst.run() outputs "first". Do not change or remove this line.
            while (value != 0) {
                conditions[0].await();
            }
            printFirst.run();
            value = 1;
            conditions[1].signal();
            lock.unlock();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            lock.lock();
            while (value != 1)
                conditions[1].await();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            value = 2;
            conditions[2].signal();
            lock.unlock();
        }

        public void third(Runnable printThird) throws InterruptedException {
            lock.lock();
            while (value != 2) {
                conditions[2].await();
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
            value = 0;
            conditions[0].signal();
            lock.unlock();
        }
    }
}
