package algorithme.backtrace;

/**
 * 1219. Path with Maximum Gold
 * https://leetcode.com/problems/path-with-maximum-gold/
 */
public class PathwithMaximumGold {
    public static void main(String[] args) {

    }

    class Solution {
        private int max = 0;

        public int getMaximumGold(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            boolean[][] visited = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] != 0) {
                        dfs(grid, visited, i, j, 0);
                    }
                }
            }
            return max;
        }

        private void dfs(int[][] grid, boolean[][] visited, int i, int j, int sum) {
            if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
                return;
            }
            if (grid[i][j] == 0 || visited[i][j]) {
                return;
            }
            visited[i][j] = true;
            sum += grid[i][j];
            max = Math.max(max, sum);
            dfs(grid, visited, i - 1, j, sum);
            dfs(grid, visited, i + 1, j, sum);
            dfs(grid, visited, i, j - 1, sum);
            dfs(grid, visited, i, j + 1, sum);
            visited[i][j] = false;
        }
    }
}
