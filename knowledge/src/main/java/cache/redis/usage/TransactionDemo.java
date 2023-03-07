package cache.redis.usage;

import cache.redis.Config;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

public class TransactionDemo {
    public static void main(String[] args) {
        try (Jedis jedis = Config.getInstance()) {
            Transaction t = jedis.multi();
            t.set("name", "liudong");
            t.incr("name");
            anotherProcess();
            t.incr("name");
            List<Object> result = t.exec();
            for (Object o : result) {
                System.out.println(o.getClass().getName() + o);
            }
            System.out.println(jedis.get("name1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void anotherProcess() {
        try (Jedis jedis = Config.getInstance()) {
            System.out.println(jedis.set("name", "dong"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}