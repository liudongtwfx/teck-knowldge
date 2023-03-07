package cache.redis;

import redis.clients.jedis.Jedis;

public interface Executor<T> {
    T execute(Jedis jedis);
}
