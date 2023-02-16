package redis.cachestratege;

/**
 * 以缓存为操作为主,数据存先存在于缓存,缓存的数据是不会过期的.<br>
 * Read-Through和Cache-Aside很相似，不同点在于程序不需要再去管理从哪去读数据（缓存还是数据库）。相反它会直接从缓存中读数据，该场景下是缓存去决定从哪查询数据。当我们比较两者的时候这是一个优势因为它会让程序代码变得更简洁。<br>
 * Write-Through下所有的写操作都经过缓存，每次我们向缓存中写数据的时候，缓存会把数据持久化到对应的数据库中去，且这两个操作都在一个事务中完成。<br>
 * 场景：
 * 需要频繁读取相同数据
 * 不能忍受数据丢失（相对Write-Behind而言）和数据不一致
 *
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
