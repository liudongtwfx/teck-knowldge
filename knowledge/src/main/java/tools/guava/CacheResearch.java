package tools.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.Weigher;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class CacheResearch {
    public static void main(String[] args) throws Exception {
        System.out.println(0x3F);
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
        for (int i = 0; i < 100; i++) {
            cache.put(i, "null");
            log.info("{} is add to cache", i);
        }
    }
}
