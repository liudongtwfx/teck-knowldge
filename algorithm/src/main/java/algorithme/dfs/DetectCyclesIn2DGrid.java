package algorithme.dfs;

public class DetectCyclesIn2DGrid {
    public static void main(String[] args) {
        new DetectCyclesIn2DGrid().bridge();
    }

    private void bridge() {
        Solution solution = new Solution();
        char[][] grid = {{'c', 'c', 'c', 'a'}, {'c', 'd', 'c', 'c'}, {'c', 'c', 'e', 'c'}, {'f', 'c', 'c', 'c'}};
        boolean b = solution.containsCycle(grid);
        System.out.println(b);
    }

    class Solution {
        public boolean containsCycle(char[][] grid) {
            boolean[][] visited = new boolean[grid.length][grid[0].length];
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (!visited[i][j] && dfs(grid, i, j, visited, -1, -1)) return true;
                }
            }
            return false;
        }

        int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        public boolean dfs(char[][] grid, int x, int y, boolean[][] visited, int prevX, int prevY) {
            if (visited[x][y]) return true;
            visited[x][y] = true;
            for (int[] move : moves) {
                int nextX = x + move[0];
                int nextY = y + move[1];
                if (nextX >= 0 &&
                        nextX < grid.length &&
                        nextY >= 0 &&
                        nextY < grid[0].length &&
                        grid[nextX][nextY] == grid[x][y] &&
                        (prevX != nextX || prevY != nextY) && //this condition checks, next block to move on matrix is not previous block.
                        dfs(grid, nextX, nextY, visited, x, y)) return true;
            }
            return false;
        }
    }
}
