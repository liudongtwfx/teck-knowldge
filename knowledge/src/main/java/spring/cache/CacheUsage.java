package spring.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CacheUsage implements CommandLineRunner {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @CacheEvict
    public String getUserName(String userId) {
        return "userId";
    }

    @Override
    public void run(String... args) throws Exception {
        redisTemplate.opsForList().set("key", 0, "value");
        redisTemplate.opsForValue().set("key", "value", 1, TimeUnit.MINUTES);
        System.out.println("set complete");
    }
}
