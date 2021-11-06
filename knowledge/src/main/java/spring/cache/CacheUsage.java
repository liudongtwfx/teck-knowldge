package spring.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class CacheUsage {
    @CacheEvict
    public String getUserName(String userId) {
        return "userId";
    }
}
