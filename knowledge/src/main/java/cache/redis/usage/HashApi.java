package cache.redis.usage;

import redis.clients.jedis.commands.HashCommands;
import redis.clients.jedis.params.ScanParams;
import redis.clients.jedis.resps.ScanResult;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class HashApi implements HashCommands {
    @Override
    public long hset(String key, String field, String value) {
        return 0;
    }

    @Override
    public long hset(String key, Map<String, String> hash) {
        return 0;
    }

    @Override
    public String hget(String key, String field) {
        return null;
    }

    @Override
    public long hsetnx(String key, String field, String value) {
        return 0;
    }

    @Override
    public String hmset(String key, Map<String, String> hash) {
        return null;
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        return null;
    }

    @Override
    public long hincrBy(String key, String field, long value) {
        return 0;
    }

    @Override
    public double hincrByFloat(String key, String field, double value) {
        return 0;
    }

    @Override
    public boolean hexists(String key, String field) {
        return false;
    }

    @Override
    public long hdel(String key, String... field) {
        return 0;
    }

    @Override
    public long hlen(String key) {
        return 0;
    }

    @Override
    public Set<String> hkeys(String key) {
        return null;
    }

    @Override
    public List<String> hvals(String key) {
        return null;
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        return null;
    }

    @Override
    public String hrandfield(String key) {
        return null;
    }

    @Override
    public List<String> hrandfield(String key, long count) {
        return null;
    }

    @Override
    public Map<String, String> hrandfieldWithValues(String key, long count) {
        return null;
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(String key, String cursor) {
        return HashCommands.super.hscan(key, cursor);
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(String key, String cursor, ScanParams params) {
        return null;
    }

    @Override
    public long hstrlen(String key, String field) {
        return 0;
    }
}
