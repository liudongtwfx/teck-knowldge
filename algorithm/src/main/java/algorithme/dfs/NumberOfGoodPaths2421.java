package algorithme.dfs;

import java.util.*;

public class NumberOfGoodPaths2421 {
    public static void main(String[] args) {

    }

    class Solution {
        private int ans = 0;
        private Map<String, Boolean> cache = new HashMap<>();

        public int numberOfGoodPaths(int[] vals, int[][] edges) {
            Map<Integer, List<Integer>> sameValueMap = new HashMap<>();
            Map<Integer, List<Integer>> edgeMap = new HashMap<>();
            for (int i = 0; i < vals.length; i++) {
                List<Integer> sameValueIndex = sameValueMap.getOrDefault(vals[i], new ArrayList<>());
                sameValueIndex.add(i);
                if (sameValueIndex.size() == 1) sameValueMap.put(vals[i], sameValueIndex);
            }
            for (int[] edge : edges) {
                addToEdgeMap(edgeMap, edge[0], edge[1]);
                addToEdgeMap(edgeMap, edge[1], edge[0]);
            }
            sameValueMap.keySet().stream().sorted().forEach(key -> {
                List<Integer> value = sameValueMap.get(key);
                if (value.size() == 1) {
                    return;
                }
                for (int i = 0; i < value.size(); i++) {
                    for (int j = i + 1; j < value.size(); j++) {
                        if (cache(edgeMap, vals, new HashSet<>(), value.get(i), value.get(j))) {
                            ans++;
                        }
                    }
                }
            });
            return ans + vals.length;
        }


        private boolean cache(Map<Integer, List<Integer>> edgeMap,
                              int[] vals, Set<Integer> visited,
                              int fromIndex, int endIndex) {
            String key = fromIndex + "_" + endIndex;
            if (cache.containsKey(key)) {
                return cache.get(key);
            }
            boolean isGood = isGoodPath(edgeMap, vals, visited, fromIndex, endIndex);
            cache.put(key, isGood);
            cache.put(endIndex + "_" + fromIndex, isGood);
            return isGood;
        }

        private boolean isGoodPath(Map<Integer, List<Integer>> edgeMap,
                                   int[] vals, Set<Integer> visited,
                                   int fromIndex, int endIndex) {
            visited.add(fromIndex);
            for (Integer next : edgeMap.getOrDefault(fromIndex, new ArrayList<>())) {
                if (next == endIndex) {
                    return true;
                }
                if (vals[next] <= vals[endIndex] &&
                        !visited.contains(next) &&
                        cache(edgeMap, vals, visited, next, endIndex)) {
                    return true;
                }
            }
            return false;
        }

        private void addToEdgeMap(Map<Integer, List<Integer>> edgeMap, Integer left, Integer right) {
            List<Integer> orDefault = edgeMap.getOrDefault(left, new ArrayList<>());
            orDefault.add(right);
            if (orDefault.size() == 1) edgeMap.put(left, orDefault);
        }
    }
}
