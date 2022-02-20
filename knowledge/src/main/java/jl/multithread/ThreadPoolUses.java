package jl.multithread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ThreadPoolUses {
    public static void main(String[] args) {
        TimeUnit unit = TimeUnit.MILLISECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(1000);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(300, 400, 3, unit, workQueue, (r, executor) -> executor.submit(r));
        for (int i = 0; i < 1000; i++) {
            threadPoolExecutor.submit(new Calculator());
        }
    }

    private static class Calculator implements Runnable {
        private static final AtomicInteger COUNT = new AtomicInteger();

        @Override
        public void run() {
            long sum = 0;
            for (int i = 0; i < 1000000000; i++) {
                sum += i;
            }
            System.out.println(sum);
            System.out.println(COUNT.incrementAndGet());
        }
    }
}
