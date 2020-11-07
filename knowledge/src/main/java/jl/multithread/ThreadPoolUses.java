package jl.multithread;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.*;

@Slf4j
public class ThreadPoolUses {
    public static void main(String[] args) {
        TimeUnit unit = TimeUnit.MILLISECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(1);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 10, 3, unit, workQueue, (r, executor) -> executor.shutdown());
        for (int i = 0; i < 100; i++) {
            final int j = i;
            Future<String> result = threadPoolExecutor.submit(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return String.valueOf(j);
            });
        }
        Arrays.stream(new String[]{"1", "2", "3"})
                .filter(s -> Objects.equals(s, "0"))
                .findFirst()
                .get();
    }
}
