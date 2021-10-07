package jl.multithread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentSkipListDemo {
    public static void main(String[] args) {
        ConcurrentSkipListMap<Integer, Integer> map = new ConcurrentSkipListMap<>();
        for (int i = 0; i < 100; i++) {
            map.put(i, i);
        }
        map.forEach((k, v) -> System.out.printf("%s %s\n", k, v));
        Map<String, String> another = new HashMap<>();
        List<String> str = new ArrayList<>();
    }
}