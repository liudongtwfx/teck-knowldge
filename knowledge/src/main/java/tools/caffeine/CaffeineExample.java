package tools.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalListener;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class CaffeineExample {
    private static final ReentrantLock LOCK = new ReentrantLock(true);

    public static void main(String[] args) {
        maxSize();
    }

    @SneakyThrows
    private static void maxSize() {
        Cache<String, DataObject> cache = Caffeine.newBuilder()
                .executor(Executors.newFixedThreadPool(1))
                .evictionListener((RemovalListener<String, DataObject>) (key, value, cause) ->
                        lockLog(() -> log.info("eviction,key:{},value:{},cause:{}", key, value.getData(), cause)))
                .removalListener((s, dataObject, removalCause) ->
                        lockLog(() -> log.info("removal,key:{},value:{},cause:{}", s, dataObject.getData(), removalCause)))
                .maximumWeight(10)
                .weigher((key, value) -> 20)
                .build();
        for (int i = 0; i < 20; i++) {
            cache.put(String.valueOf(i), DataObject.get(String.format("%010d", i)));
        }
        Thread.sleep(1000);
        int count = 0;
        for (int i = 0; i < 20; i++) {
            if (cache.getIfPresent(String.valueOf(i)) != null) {
                count++;
                System.out.println(i);
            }
        }
        System.out.println(count);
    }

    private static void lockLog(Logger logger) {
        LOCK.lock();
        try {
            logger.log();
            throw new IllegalStateException("");
        } catch (Exception e) {
            // e.printStackTrace();
        } finally {
            LOCK.unlock();
        }

    }

    private interface Logger {
        void log();
    }

    private static class DataObject {
        private static int objectCounter = 0;
        private final String data;
        // standard constructors/getters

        public DataObject(String data) {
            this.data = data;
        }

        public static DataObject get(String data) {
            objectCounter++;
            return new DataObject(data);
        }

        public String getData() {
            return data;
        }
    }
}
