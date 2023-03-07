package cache.cachestratege;

public interface StorageOperate<T> {
    void save(String key, T value);

    void update(String key, T value);

    T query(String key);

    void delete(String key);
}
