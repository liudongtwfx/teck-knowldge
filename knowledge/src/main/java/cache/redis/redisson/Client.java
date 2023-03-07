package cache.redis.redisson;

import com.google.gson.Gson;
import org.redisson.Redisson;
import org.redisson.api.RBuckets;
import org.redisson.api.RedissonClient;

import java.util.Map;

public class Client {
    public static void main(String[] args) {
        RedissonClient redissonClient = Redisson.create();
        RBuckets buckets = redissonClient.getBuckets();
        // buckets
        Map<String, Object> name = buckets.get("name");
        System.out.println(new Gson().toJson(name));
    }
}
