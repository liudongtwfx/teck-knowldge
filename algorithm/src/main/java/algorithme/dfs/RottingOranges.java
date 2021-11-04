package algorithme.dfs;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * @author liudong
 */
public class RottingOranges {

    public static void main(String[] args) {
        new RottingOranges().bridge();
    }

    private void bridge() {
        String json = "[[2,1,1],[1,1,1],[0,1,2]]";
        Type type = new TypeToken<int[][]>() {
        }.getType();
        Gson gson = new Gson();
        int[][] grid = gson.fromJson(json, type);
        System.out.println(new Solution().orangesRotting(grid));
    }

    static class Solution {
        public int orangesRotting(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int[][] rottingSteps = new int[m][n];
            Arrays.stream(rottingSteps).forEach(line -> Arrays.fill(line, Integer.MAX_VALUE));
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 2) {
                        dfs(grid, new boolean[m][n], rottingSteps, i, j, 0);
                    }
                }
            }
            int ans = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 0) {
                        continue;
                    }
                    if (rottingSteps[i][j] == Integer.MAX_VALUE) {
                        return -1;
                    }
                    ans = Math.max(ans, rottingSteps[i][j]);
                }
            }
            return ans;
        }

        private void dfs(int[][] grid, boolean[][] visited, int[][] rottingSteps, int i, int j, int currentStep) {
            if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
                return;
            }
            if (grid[i][j] == 0 || currentStep >= rottingSteps[i][j]) {
                return;
            }
            visited[i][j] = true;
            rottingSteps[i][j] = currentStep;
            dfs(grid, visited, rottingSteps, i + 1, j, currentStep + 1);
            dfs(grid, visited, rottingSteps, i - 1, j, currentStep + 1);
            dfs(grid, visited, rottingSteps, i, j + 1, currentStep + 1);
            dfs(grid, visited, rottingSteps, i, j - 1, currentStep + 1);
        }
    }
}
