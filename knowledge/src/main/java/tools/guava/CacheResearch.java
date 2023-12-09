package tools.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.Weigher;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CacheResearch {
    public static void main(String[] args) throws Exception {
        withWeigher();
    }


    @SneakyThrows
    private static void withWeigher() {
        Cache<Integer, String> cache = CacheBuilder.newBuilder()
                .maximumWeight(100)
                .weigher((Weigher<Integer, String>) (key, value) -> key)
                .removalListener(notification -> {
                    log.info("Removal:{},key:{}", notification.getCause(), notification.getKey());
                })
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .build();

        ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        pool.prestartAllCoreThreads();

        final long expect = System.currentTimeMillis() + 1000;

        for (int i = 0; i < 2; i++) {
            final int key = i;
            pool.execute(() -> {
                try {
                    while (System.currentTimeMillis() < expect) {
                    }
                    cache.get(1, () -> {
                        System.out.println("call " + key);
                        return "1";
                    });
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        pool.shutdown();
    }
}
