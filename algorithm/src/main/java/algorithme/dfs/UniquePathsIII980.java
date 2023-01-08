package algorithme.dfs;

import algorithme.InputUtils;

public class UniquePathsIII980 {
    public static void main(String[] args) {
        new UniquePathsIII980().bridge();
    }

    private void bridge() {
        String input = "[[1,0,0,0],[0,0,0,0],[0,0,2,-1]]";
        int[][] grid = InputUtils.convertToArray(input);
        System.out.println(new Solution().uniquePathsIII(grid));
    }

    class Solution {
        private int ans = 0;
        private int expectCount = 0;
        private final int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

        public int uniquePathsIII(int[][] grid) {
            int startI = 0, startJ = 0;
            int endI = 0, endJ = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1) {
                        startI = i;
                        startJ = j;
                    } else if (grid[i][j] == 2) {
                        endI = i;
                        endJ = j;
                    } else if (grid[i][j] == 0) {
                        expectCount++;
                    }
                }
            }
            dfs(grid, startI, startJ, endI, endJ, new boolean[grid.length][grid[0].length], 0);
            return ans;
        }

        private void dfs(int[][] grid, int i, int j, int endI, int endJ, boolean[][] visited, int count) {
            if (i >= grid.length || i < 0 || j >= grid[0].length || j < 0) {
                return;
            }
            if (i == endI && j == endJ) {
                if (count == expectCount + 1) {
                    ans++;
                }
                return;
            }
            if (visited[i][j] || grid[i][j] == -1) {
                return;
            }
            visited[i][j] = true;
            for (int[] d : dir) {
                dfs(grid, i + d[0], j + d[1], endI, endJ, visited, count + 1);
            }
            visited[i][j] = false;
        }
    }
}
