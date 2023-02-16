package algorithme.dfs;

import algorithme.InputUtils;

import java.util.ArrayList;
import java.util.List;

public class MinimumFuelCostToReportToTheCapital2477 {
    public static void main(String[] args) {
        new MinimumFuelCostToReportToTheCapital2477().bridge();
    }

    private void bridge() {
        String input = "[[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]]";
        int seats = 2;
        System.out.println(new Solution().minimumFuelCost(InputUtils.convertToArray(input), seats));
    }

    class Solution {
        private long ans;
        public long minimumFuelCost(int[][] roads, int seats) {
            if (roads.length == 0) return 0;
            int n = roads.length + 1;
            List<Integer>[] treeNodes = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                treeNodes[i] = new ArrayList<>();
            }
            for (int[] road : roads) {
                treeNodes[road[0]].add(road[1]);
                treeNodes[road[1]].add(road[0]);
            }
            for (Integer next : treeNodes[0]) {
                dfs(treeNodes, seats, next, 0);
            }
            return ans;
        }

        private long dfs(List<Integer>[] treeNodes, int seats, int currentNode, int prev) {
            long childrenCount = 1;
            for (Integer node : treeNodes[currentNode]) {
                if (prev != node) {
                    childrenCount += dfs(treeNodes, seats, node, currentNode);
                }
            }
            long carCount = (childrenCount % seats == 0 ? childrenCount / seats : (childrenCount / seats + 1));
            ans += carCount;
            return childrenCount;
        }
    }
}
