package cache;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;

public class GuavaBloomFilter {
    @SuppressWarnings("all")
    public static void main(String[] args) {
        BloomFilter<String> bf = BloomFilter.create((Funnel<String>) (from, into) -> into.putBytes(from.getBytes()), 100);
        bf.put("name");
        bf.put("liudong");
        boolean test = bf.mightContain("nam");
        System.out.println(test);
    }
}
