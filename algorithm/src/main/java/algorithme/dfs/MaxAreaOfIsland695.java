package algorithme.dfs;

public class MaxAreaOfIsland695 {
    public static void main(String[] args) {

    }


    class Solution {
        public int maxAreaOfIsland(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            boolean[][] visited = new boolean[m][n];
            int ans = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j] && grid[i][j] == 1) {
                        ans = Math.max(ans, computeMaxIsland(grid, visited, i, j));
                    }
                }
            }
            return ans;
        }

        private int computeMaxIsland(int[][] grid, boolean[][] visited, int i, int j) {
            if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) return 0;
            if (visited[i][j] || grid[i][j] == 0) {
                return 0;
            }
            visited[i][j] = true;
            return computeMaxIsland(grid, visited, i - 1, j) +
                    computeMaxIsland(grid, visited, i + 1, j) +
                    computeMaxIsland(grid, visited, i, j - 1) +
                    computeMaxIsland(grid, visited, i, j + 1) + 1;
        }
    }
}
