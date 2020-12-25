package tools.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RateLimiterUtils {
    public static void main(String[] args) {
        create();
    }

    private static void create() {
        RateLimiter rateLimiter = RateLimiter.create(2.0);
        ExecutorService executorService = new ThreadPoolExecutor(1, 20, 10, TimeUnit.DAYS, new ArrayBlockingQueue<>(1024));
        final long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    rateLimiter.acquire();
                    System.out.println(System.currentTimeMillis() - start);
                }
            });
        }
    }
}
