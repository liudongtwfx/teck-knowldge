package cache.redis.usage;

import cache.redis.Config;
import cache.redis.Executor;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.args.ListDirection;
import redis.clients.jedis.args.ListPosition;
import redis.clients.jedis.commands.ListCommands;
import redis.clients.jedis.params.LPosParams;
import redis.clients.jedis.resps.KeyedListElement;
import redis.clients.jedis.util.KeyValue;

import java.util.List;

@Slf4j
public class ListApi implements ListCommands {

    public static void main(String[] args) {
        ListApi listApi = new ListApi();
        // long pushResult = listApi.rpush("key", "I", "am", "a", "student");
        // System.out.println(pushResult);
        List<String> key = listApi.lrange("key", 0, -1);
        System.out.println(key);
    }

    private <T> T execute(Executor<T> executor) {
        try (Jedis jedis = Config.getInstance()) {
            return executor.execute(jedis);
        } catch (Exception e) {
            log.error("get redis exception", e);
            throw new RuntimeException("get redis exception");
        }
    }

    @Override
    public long rpush(String key, String... strings) {
        return execute(jedis -> jedis.rpush(key, strings));
    }

    @Override
    public long lpush(String key, String... strings) {
        return execute(jedis -> jedis.lpush(key, strings));
    }

    @Override
    public long llen(String key) {
        return execute(jedis -> jedis.llen(key));
    }

    @Override
    public List<String> lrange(String key, long start, long stop) {
        return execute(jedis -> jedis.lrange(key, start, stop));
    }

    @Override
    public String ltrim(String key, long start, long stop) {
        return execute(jedis -> jedis.ltrim(key, start, stop));
    }

    @Override
    public String lindex(String key, long index) {
        return execute(jedis -> jedis.lindex(key, index));
    }

    @Override
    public String lset(String key, long index, String value) {
        return execute(jedis -> jedis.lset(key, index, value));
    }

    @Override
    public long lrem(String key, long count, String value) {
        return 0;
    }

    @Override
    public String lpop(String key) {
        return null;
    }

    @Override
    public List<String> lpop(String key, int count) {
        return null;
    }

    @Override
    public Long lpos(String key, String element) {
        return null;
    }

    @Override
    public Long lpos(String key, String element, LPosParams params) {
        return null;
    }

    @Override
    public List<Long> lpos(String key, String element, LPosParams params, long count) {
        return null;
    }

    @Override
    public String rpop(String key) {
        return null;
    }

    @Override
    public List<String> rpop(String key, int count) {
        return null;
    }

    @Override
    public long linsert(String key, ListPosition where, String pivot, String value) {
        return 0;
    }

    @Override
    public long lpushx(String key, String... strings) {
        return 0;
    }

    @Override
    public long rpushx(String key, String... strings) {
        return 0;
    }

    @Override
    public List<String> blpop(int timeout, String... keys) {
        return null;
    }

    @Override
    public List<String> blpop(int timeout, String key) {
        return null;
    }

    @Override
    public KeyedListElement blpop(double timeout, String... keys) {
        return null;
    }

    @Override
    public KeyedListElement blpop(double timeout, String key) {
        return null;
    }

    @Override
    public List<String> brpop(int timeout, String... keys) {
        return null;
    }

    @Override
    public List<String> brpop(int timeout, String key) {
        return null;
    }

    @Override
    public KeyedListElement brpop(double timeout, String... keys) {
        return null;
    }

    @Override
    public KeyedListElement brpop(double timeout, String key) {
        return null;
    }

    @Override
    public String rpoplpush(String srckey, String dstkey) {
        return null;
    }

    @Override
    public String brpoplpush(String source, String destination, int timeout) {
        return null;
    }

    @Override
    public String lmove(String srcKey, String dstKey, ListDirection from, ListDirection to) {
        return null;
    }

    @Override
    public String blmove(String srcKey, String dstKey, ListDirection from, ListDirection to, double timeout) {
        return null;
    }

    @Override
    public KeyValue<String, List<String>> lmpop(ListDirection direction, String... keys) {
        return null;
    }

    @Override
    public KeyValue<String, List<String>> lmpop(ListDirection direction, int count, String... keys) {
        return null;
    }

    @Override
    public KeyValue<String, List<String>> blmpop(long timeout, ListDirection direction, String... keys) {
        return null;
    }

    @Override
    public KeyValue<String, List<String>> blmpop(long timeout, ListDirection direction, int count, String... keys) {
        return null;
    }
}
