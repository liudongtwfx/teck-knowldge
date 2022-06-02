package redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Config {
    public static final JedisPool JEDIS;
    private static final JedisPoolConfig config = new JedisPoolConfig();

    static {
        config.setMaxIdle(10000);
        config.setMaxTotal(10000);
        JEDIS = new JedisPool(config, "localhost", 6379);
    }
}
