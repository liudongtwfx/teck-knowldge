package tools.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ObjectPoolDemo {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        GenericObjectPool<String> objectPool = new GenericObjectPool<>(new PooledObjectFactory());
        objectPool.setMaxIdle(20);
        objectPool.setMinIdle(10);
        objectPool.setMaxTotal(20);
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                String s = null;
                try {
                    s = objectPool.borrowObject();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(s);
                // objectPool.returnObject(s);
            });
        }
    }

    private static class PooledObjectFactory extends BasePooledObjectFactory<String> {
        private static final AtomicInteger COUNT = new AtomicInteger();

        @Override
        public String create() throws Exception {
            return String.valueOf(COUNT.getAndIncrement());
        }

        @Override
        public PooledObject<String> wrap(String obj) {
            return new DefaultPooledObject<>(obj);
        }
    }
}
