package algorithme.dfs;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MostStonesRemovedWithSameRowOrColumn946 {
    public static void main(String[] args) {
        new MostStonesRemovedWithSameRowOrColumn946().bridge();
    }

    private void bridge() {
        String json = "[[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]";
        int[][] stones = new Gson().fromJson(json, new TypeToken<int[][]>() {
        }.getType());
        System.out.println(new Solution().removeStones(stones));
    }

    class Solution {
        int max = 0;

        public int removeStones(int[][] stones) {
            Map<Integer, Integer> rowsMap = new HashMap<>();
            Map<Integer, Integer> columnMap = new HashMap<>();
            for (int i = 0; i < stones.length; i++) {
                rowsMap.merge(stones[i][0], 1, Integer::sum);
                columnMap.merge(stones[i][1], 1, Integer::sum);
            }
            for (int i = 0; i < stones.length; i++) {
                if (rowsMap.getOrDefault(stones[i][0], 0) == 1 &&
                        columnMap.getOrDefault(stones[i][1], 0) == 1) {
                    continue;
                }
                Set<Integer> removed = new HashSet<>();
                rowsMap.merge(stones[i][0], 1, (a, b) -> a - b);
                columnMap.merge(stones[i][1], 1, (a, b) -> a - b);
                removed.add(i);
                dfs(stones, removed, rowsMap, columnMap);
                rowsMap.merge(stones[i][0], 1, Integer::sum);
                columnMap.merge(stones[i][1], 1, Integer::sum);
            }
            return max;
        }

        private void dfs(int[][] stones, Set<Integer> removed, Map<Integer, Integer> rowsMap,
                         Map<Integer, Integer> columnMap) {
            for (int i = 0; i < stones.length; i++) {
                if (removed.contains(i)) continue;
                if (rowsMap.getOrDefault(stones[i][0], 0) > 1 ||
                        columnMap.getOrDefault(stones[i][1], 0) > 1) {
                    rowsMap.merge(stones[i][0], 1, (a, b) -> a - b);
                    columnMap.merge(stones[i][1], 1, (a, b) -> a - b);
                    removed.add(i);
                    max = Math.max(max, removed.size());
                    dfs(stones, removed, rowsMap, columnMap);
                    rowsMap.merge(stones[i][0], 1, Integer::sum);
                    columnMap.merge(stones[i][1], 1, Integer::sum);
                    removed.remove(i);
                }
            }
        }
    }
}
