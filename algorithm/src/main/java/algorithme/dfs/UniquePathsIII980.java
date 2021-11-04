package algorithme.dfs;

public class UniquePathsIII980 {
    public static void main(String[] args) {
        new UniquePathsIII980().bridge();
    }


    private void bridge() {
        int[][] grid = new int[5][5];
        grid[0][0] = 1;
        grid[4][4] = 2;
        System.out.println(new Solution().uniquePathsIII(grid));
    }

    class Solution {
        private int m;
        private int n;
        private int ans;
        private int walkOverCount;
        private int invoke;

        public int uniquePathsIII(int[][] grid) {
            m = grid.length;
            n = grid[0].length;
            int[] startPoint = new int[]{};
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        startPoint = new int[]{i, j};
                    } else if (grid[i][j] == 0) {
                        walkOverCount++;
                    }
                }
            }
            dfs(grid, startPoint[0], startPoint[1], new boolean[m][n], 0);
            System.out.println(invoke);
            return ans;
        }

        private void dfs(int[][] grid, int i, int j, boolean[][] visited, int currentWorkOverCount) {
            invoke++;
            System.out.println(invoke);
            if (i < 0 || j < 0 || i >= m || j >= n) {
                return;
            }
            if (visited[i][j] || grid[i][j] == -1) {
                return;
            }
            if (grid[i][j] == 2 && currentWorkOverCount == walkOverCount + 1) {
                ans++;
                return;
            }
            visited[i][j] = true;
            dfs(grid, i + 1, j, visited, currentWorkOverCount + 1);
            dfs(grid, i, j + 1, visited, currentWorkOverCount + 1);
            dfs(grid, i - 1, j, visited, currentWorkOverCount + 1);
            dfs(grid, i, j - 1, visited, currentWorkOverCount + 1);
            visited[i][j] = false;
        }
    }
}
