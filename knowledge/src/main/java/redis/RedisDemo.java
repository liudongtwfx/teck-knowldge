package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ScanResult;

public class RedisDemo {
    private static final JedisPoolConfig config = new JedisPoolConfig();
    private static final JedisPool JEDIS;

    static {
        config.setMaxIdle(10000);
        config.setMaxTotal(10000);
        JEDIS = new JedisPool(config, "localhost", 6379);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(new SortedSetRunner(String.valueOf(i), i)).start();
        }

        ScanResult<String> scan = JEDIS.getResource().scan("1");
        String cursor = scan.getCursor();
        System.out.println(cursor);
        scan.getResult().forEach(System.out::println);
    }

    private static final class SetKeyRunner implements Runnable {
        private final String key;
        private final String value;

        public SetKeyRunner(String key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public void run() {
            Jedis resource = JEDIS.getResource();
            resource.set(key, value);
            resource.close();
        }
    }

    private static final class SortedSetRunner implements Runnable {
        private static final String KEY = "sorted_set";
        private String member;
        private long score;

        public SortedSetRunner(String member, long score) {
            this.member = member;
            this.score = score;
        }

        @Override
        public void run() {
            Jedis resource = JEDIS.getResource();
            resource.zadd(KEY, score, member);
            resource.close();
        }
    }
}
