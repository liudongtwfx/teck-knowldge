package person.liudong;

import java.util.concurrent.ConcurrentSkipListMap;

public class Main {
    public static void main(String[] args) {
        ConcurrentSkipListMap<Integer, Integer> skipListMap = new ConcurrentSkipListMap<>();
        for (int i = 1; i <= 19; i++) {
            skipListMap.put(i, i);
        }
        System.out.println(skipListMap.get(8));
    }
}