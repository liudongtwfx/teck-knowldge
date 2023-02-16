package redis.cachestratege;

/***
 * <a href='https://www.cnblogs.com/mrcharleshu/p/13193505.html'>参考文档 </a>
 */
public class App {
    public static void main(String[] args) {
        CacheAsideStrategy cacheAsideStrategy = new CacheAsideStrategy(CacheAsideStrategy.WriteOrderMode.CACHE_FIRST.name());
        cacheAsideStrategy.mySqlStorageOperation.save("", "");
    }
}
