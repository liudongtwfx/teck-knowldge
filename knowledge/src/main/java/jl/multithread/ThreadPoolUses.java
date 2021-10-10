package jl.multithread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class ThreadPoolUses {
    public static void main(String[] args) {
        TimeUnit unit = TimeUnit.MILLISECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(100);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 10, 3, unit, workQueue, (r, executor) -> executor.shutdown());
        for (int i = 0; i < 100; i++) {
            final int j = i;
            Future<String> result = threadPoolExecutor.submit(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return String.valueOf(j);
            });
        }
        threadPoolExecutor.shutdownNow();
    }
}
