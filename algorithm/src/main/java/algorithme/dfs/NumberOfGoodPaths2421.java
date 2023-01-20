package algorithme.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfGoodPaths2421 {
    public static void main(String[] args) {

    }

    class Solution {
        public int numberOfGoodPaths(int[] vals, int[][] edges) {
            Map<Integer, List<Integer>> edgeMap = new HashMap<>();
            for (int[] edge : edges) {
                addToEdgeMap(edgeMap, edge[0], edge[1]);
                addToEdgeMap(edgeMap, edge[1], edge[0]);
            }

            int ans = 0;
            return ans;
        }

        private void addToEdgeMap(Map<Integer, List<Integer>> edgeMap, int from, int to) {
            List<Integer> edgeList = edgeMap.getOrDefault(from, new ArrayList<>());
            edgeList.add(to);
            if (edgeList.size() == 1) {
                edgeMap.put(from, edgeList);
            }
        }
    }
}
