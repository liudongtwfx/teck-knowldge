package algorithme.array;

import java.util.Arrays;

public class MinimumOperationsToMakeAUniValueGrid {
    public static void main(String[] args) {
        new MinimumOperationsToMakeAUniValueGrid().bridge();
    }

    private void bridge() {
        int[][] grid = {{2, 4}, {6, 8}};
        int x = 2;
        System.out.println(new Solution().minOperations(grid, x));
    }

    class Solution {
        public int minOperations(int[][] grid, int x) {
            int m = grid.length;
            int n = grid[0].length;
            int[] dp = new int[m * n];
            int remainder = grid[0][0] % x;
            int index = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] % x != remainder) {
                        return -1;
                    }
                    dp[index++] = (grid[i][j] - remainder) / x;
                }
            }
            Arrays.sort(dp);
            int medium = dp[m * n / 2];
            int ans = 0;
            for (int i = 0; i < m * n; i++) {
                ans += Math.abs(dp[i] - medium);
            }
            return ans;
        }
    }
}
