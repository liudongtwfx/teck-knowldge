package redis.cachestratege;

public interface Cacheable<T> extends StorageOperate<T>{
    void invalidate(String key);
}
