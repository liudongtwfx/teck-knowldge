package algorithme.array;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class AvoidFloodInTheCity1488 {
    public static void main(String[] args) {

    }


    class Solution {
        public int[] avoidFlood(int[] rains) {
            int[] res = new int[rains.length];
            Map<Integer, Integer> map = new HashMap<>();
            TreeSet<Integer> set = new TreeSet<>();

            for (int i = 0; i < rains.length; i++) {
                if (rains[i] == 0) {
                    set.add(i);
                    res[i] = 1;
                } else {
                    if (map.containsKey(rains[i])) {
                        int last = map.get(rains[i]);
                        Integer dry = set.ceiling(last);
                        if (dry == null)
                            return new int[0];
                        res[dry] = rains[i];
                        set.remove(dry);
                    }
                    res[i] = -1;
                    map.put(rains[i], i);
                }
            }
            return res;
        }
    }
}
