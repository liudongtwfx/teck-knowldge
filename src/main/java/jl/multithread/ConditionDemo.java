package jl.multithread;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
    private final ReentrantLock lock;
    private final Condition printCondition;

    ConditionDemo() {
        lock = new ReentrantLock();
        printCondition = lock.newCondition();
    }

    public static void main(String[] args) {
        ConditionDemo demo = new ConditionDemo();
        demo.printNumberAndChar();
    }

    void printNumberAndChar() {
        new Thread(new NumberPrinter()).start();
        new Thread(new CharPrinter()).start();
    }


    private class NumberPrinter implements Runnable {
        private int num = 0;

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                lock.lock();
                System.out.println(num++);
                System.out.println(num++);
                printCondition.signal();
                printCondition.await();
                lock.unlock();
            }
        }
    }


    private class CharPrinter implements Runnable {
        private int num = 0;

        @Override
        @SneakyThrows
        public void run() {
            while (true) {
                lock.lock();
                char c = (char) ('a' + num++);
                System.out.println(c);
                if (c == 'z') {
                    System.exit(0);
                }
                printCondition.signal();
                printCondition.await();
                lock.unlock();
            }
        }
    }
}
