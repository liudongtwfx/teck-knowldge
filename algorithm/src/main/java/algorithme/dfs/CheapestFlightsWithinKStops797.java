package algorithme.dfs;

import algorithme.InputUtils;

import java.util.ArrayList;
import java.util.List;

public class CheapestFlightsWithinKStops797 {
    public static void main(String[] args) {
        new CheapestFlightsWithinKStops797().bridge();
    }

    private void bridge() {
        String input = "[[0,1,5],[1,2,5],[0,3,2],[3,1,2],[1,4,1],[4,2,1]]";
        System.out.println(new Solution().findCheapestPrice(5, InputUtils.convertToArray(input), 0, 2, 2));
    }

    class Solution {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            List<int[]>[] flightsMap = new List[n + 1];
            for (int[] flight : flights) {
                if (flightsMap[flight[0]] == null) {
                    flightsMap[flight[0]] = new ArrayList<>();
                }
                flightsMap[flight[0]].add(new int[]{flight[1], flight[2]});
            }
            int ans = dfs(flightsMap, src, dst, k, new boolean[n + 1], 0);
            return ans == Integer.MAX_VALUE ? -1 : ans;
        }

        private int dfs(List<int[]>[] flightsMap, int src, int dst, int k, boolean[] visited, int stop) {
            if (src == dst) {
                return 0;
            }
            if (stop > k) {
                return -1;
            }
            if (visited[src]) return -1;
            // if (cache[src] != -1) return cache[src];
            visited[src] = true;
            List<int[]> toList = flightsMap[src];
            if (toList == null || toList.isEmpty()) {
                return -1;
            }
            int min = Integer.MAX_VALUE;
            for (int[] to : toList) {
                int next = dfs(flightsMap, to[0], dst, k, visited, stop + 1);
                if (next != -1) {
                    min = Math.min(min, next + to[1]);
                }
            }
            visited[src] = false;
            return min != Integer.MAX_VALUE ? min : -1;
        }
    }
}
