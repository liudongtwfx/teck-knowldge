package redis.cachestratege;


public class RedisStorageOperation implements Cacheable<String> {

    @Override
    public void save(String key, String value) {

    }

    @Override
    public void update(String key, String value) {

    }

    @Override
    public String query(String key) {
        return null;
    }

    @Override
    public void delete(String key) {

    }

    @Override
    public void invalidate(String key) {
        delete(key);
    }
}
