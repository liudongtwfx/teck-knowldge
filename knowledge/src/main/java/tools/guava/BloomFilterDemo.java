package tools.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import static java.nio.charset.StandardCharsets.UTF_8;

public class BloomFilterDemo {
    public static void main(String[] args) {
        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(UTF_8), 1000);
        for (int i = 0; i < 10000; i++) {
            bloomFilter.put(String.valueOf(i));
        }
        int count = 0;
        for (int i = 10000; i < 100000; i++) {
            if (bloomFilter.mightContain(String.valueOf(i))) {
                count++;
            }
        }
        System.out.println(count);
    }
}
