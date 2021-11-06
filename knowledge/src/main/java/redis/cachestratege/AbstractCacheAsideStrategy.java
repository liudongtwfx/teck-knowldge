package redis.cachestratege;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractCacheAsideStrategy implements StorageOperate<String> {
    protected MySqlStorageOperation mySqlStorageOperation;
    protected RedisStorageOperation redisStorageOperation;

    protected ExecutorService executorService = new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1000), new ThreadFactory() {
        private final AtomicInteger THREAD_COUNT = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            ThreadGroup group = Thread.currentThread().getThreadGroup();
            String threadName = String.format("-%s", THREAD_COUNT.getAndIncrement());
            Thread t = new Thread(group, r, threadName, 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    });
}
