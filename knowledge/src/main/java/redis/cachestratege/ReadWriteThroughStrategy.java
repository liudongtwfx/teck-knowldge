package redis.cachestratege;

/**
 * 以缓存为操作为主,数据存先存在于缓存,缓存的数据是不会过期的.
 * 场景：
 * 需要频繁读取相同数据
 * 不能忍受数据丢失（相对Write-Behind而言）和数据不一致
 */
public class ReadWriteThroughStrategy extends AbstractCacheAsideStrategy {
    /**
     * redisStorageOperation 包含了更新数据库的操作  (同一个事务)
     *
     * @param key
     * @return
     */
    @Override
    public void save(String key, String value) {
        redisStorageOperation.update(key, value);
    }

    /**
     * redisStorageOperation 包含了更新数据库的操作 (同一个事务)
     *
     * @param key
     * @return
     */
    @Override
    public void update(String key, String value) {
        redisStorageOperation.update(key, value);
    }

    /**
     * redisStorageOperation 包含了查db并刷写到缓存的过程
     *
     * @param key
     * @return
     */
    @Override
    public String query(String key) {
        return redisStorageOperation.query(key);
    }

    /**
     * redisStorageOperation 包含了更新数据库的操作  (同一个事务)
     *
     * @param key
     * @return
     */
    @Override
    public void delete(String key) {
        redisStorageOperation.delete(key);
    }
}
