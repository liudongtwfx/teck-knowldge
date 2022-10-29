package algorithme.design;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class TimeBasedKeyValueStore981 {
    public static void main(String[] args) {

    }

    class TimeMap {
        private Map<String, NavigableMap<Integer, String>> timeMap;

        public TimeMap() {
            timeMap = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            NavigableMap<Integer, String> t = timeMap.getOrDefault(key, new TreeMap<>());
            t.put(timestamp, value);
            if (t.size() == 1) {
                timeMap.put(key, t);
            }
        }

        public String get(String key, int timestamp) {
            NavigableMap<Integer, String> t = timeMap.get(key);
            if (t == null || t.isEmpty()) {
                return null;
            }
            Map.Entry<Integer, String> entry = t.floorEntry(timestamp);
            return entry != null ? entry.getValue() : null;
        }
    }
}
