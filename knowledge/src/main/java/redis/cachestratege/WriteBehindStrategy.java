package redis.cachestratege;

/**
 * Write-Behind和Write-Through在“程序只和缓存交互且只能通过缓存写数据”这一点上很相似。
 * 不同点在于Write-Through会把数据立即写入数据库中，而Write-Behind会在一段时间之后（或是被其他方式触发）把数据一起写入数据库，
 * 这个异步写操作是Write-Behind的最大特点。
 */
public class WriteBehindStrategy extends AbstractCacheAsideStrategy {
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
