package redis.cachestratege;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 用于读操作较多.实现简单.
 * Cache Aside
 */
public class CacheAsideStrategy extends AbstractCacheAsideStrategy {

    private WriteOperation writeOperation;

    CacheAsideStrategy(String mode) {
        WriteOrderMode writeOrderMode = WriteOrderMode.valueOf(mode);
        if (writeOrderMode == WriteOrderMode.CACHE_FIRST) {
            this.writeOperation = new CacheFirstWriteOperation(mySqlStorageOperation, redisStorageOperation);
        }
        if (writeOrderMode == WriteOrderMode.DB_FIRST) {
            this.writeOperation = new DbFirstWriteOperation(mySqlStorageOperation, redisStorageOperation);
            executorService.submit(new MessageConsumer(redisStorageOperation));
        }
    }

    @Override
    public void save(String key, String value) {
        writeOperation.save(key, value);
    }

    @Override
    public void update(String key, String value) {
        writeOperation.update(key, value);
    }

    /**
     * 查询缓存,如果不存在那么就读取数据库并更新到缓存当中.
     *
     * @param key
     * @return
     */
    @Override
    public String query(String key) {
        String cacheValue = redisStorageOperation.query(key);
        if (cacheValue != null) {
            return cacheValue;
        }
        String dbValue = mySqlStorageOperation.query(key);
        // 异步刷新缓存
        new Thread(() -> redisStorageOperation.save(key, dbValue)).start();
        return dbValue;
    }

    @Override
    public void delete(String key) {
        writeOperation.delete(key);
    }

    public enum WriteOrderMode {
        /**
         *
         */
        DB_FIRST, CACHE_FIRST
    }

    interface WriteOperation {
        void save(String key, String value);

        void update(String key, String value);

        void delete(String key);
    }


    @AllArgsConstructor
    static class CacheFirstWriteOperation implements WriteOperation {
        protected MySqlStorageOperation mySqlStorageOperation;
        protected RedisStorageOperation redisStorageOperation;

        @Override
        public void save(String key, String value) {
            mySqlStorageOperation.save(key, value);
            // 不写缓存，查的时候若缓存无数据，从db查出来后写到缓存
        }

        /**
         * 存在并发场景，另外一个线程发现缓存不存在，从数据库查询出旧值后写到缓存中，导致缓存还是旧的数据
         * 解决方案方案：延时双删
         * sleep的时间大于第二个线程读数据+写缓存的时间
         *
         * @param value
         */
        @Override
        public void update(String key, String value) {
            // 第一次删除
            redisStorageOperation.invalidate(key);
            // 数据库写
            mySqlStorageOperation.save(key, value);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //  第二次删除
            redisStorageOperation.invalidate(key);
        }

        @Override
        public void delete(String key) {
            // 第一次删除
            redisStorageOperation.invalidate(key);
            // 数据库写
            mySqlStorageOperation.delete(key);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //  第二次删除
            redisStorageOperation.invalidate(key);
        }
    }

    @AllArgsConstructor
    static class DbFirstWriteOperation implements WriteOperation {
        private static final BlockingQueue<Pair<String, String>> MESSAGE_QUEUE = new ArrayBlockingQueue<>(1024);
        protected MySqlStorageOperation mySqlStorageOperation;
        protected RedisStorageOperation redisStorageOperation;

        @Override
        public void save(String key, String value) {
            mySqlStorageOperation.save(key, value);
            // 不写缓存，查的时候若缓存无数据，从db查出来后写到缓存
        }

        /**
         * @param key
         * @param value
         */
        @Override
        public void update(String key, String value) {
            try {
                mySqlStorageOperation.update(key, value);
            } catch (Exception e) {
                return;
            }
            MESSAGE_QUEUE.offer(Pair.of(key, value));
        }

        @Override
        public void delete(String key) {
            try {
                mySqlStorageOperation.delete(key);
            } catch (Exception e) {
                return;
            }
            MESSAGE_QUEUE.offer(Pair.of(key, null));
        }
    }

    @AllArgsConstructor
    static class MessageConsumer implements Runnable {
        private RedisStorageOperation redisStorageOperation;

        @Override
        public void run() {
            while (true) {
                Pair<String, String> poll;
                try {
                    poll = DbFirstWriteOperation.MESSAGE_QUEUE.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    continue;
                }
                if (poll.getValue() == null) {
                    redisStorageOperation.delete(poll.getKey());
                } else {
                    redisStorageOperation.update(poll.getKey(), poll.getValue());
                }
            }
        }
    }
}
