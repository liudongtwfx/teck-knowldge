package jl;

import java.util.IdentityHashMap;
import java.util.Map;

public class StringBuilder {
    public static void main(String[] args) {
        Map<Integer, String> map = new IdentityHashMap<>();
        map.put(100, "a");
        map.put(100, "a");
        map.put(100, "a");
        map.put(100, "a");
        System.out.println(map.size());
    }
}
