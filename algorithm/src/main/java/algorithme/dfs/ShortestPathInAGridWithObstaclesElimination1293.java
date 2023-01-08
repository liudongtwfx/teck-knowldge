package algorithme.dfs;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShortestPathInAGridWithObstaclesElimination1293 {
    public static void main(String[] args) {
        new ShortestPathInAGridWithObstaclesElimination1293().bridge();
        StringBuilder sb = new StringBuilder();
        System.out.println((double) 60 * 24 * 365 * 0.00005);
    }

    private void bridge() {
        String json = "[[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]]";
        int[][] grid = new Gson().fromJson(json, new TypeToken<int[][]>() {
        }.getType());

        System.out.println(new Solution().shortestPath(grid, 1));
    }


    class Solution {
        private final int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        public int shortestPath(int[][] grid, int k) {
            List<int[]> queue = new ArrayList<>();
            queue.add(new int[]{0, 0, k});
            int step = 0;
            int[][] visited = new int[grid.length][grid[0].length];
            Arrays.stream(visited).forEach(line -> Arrays.fill(line, -1));
            visited[0][0] = k;
            while (!queue.isEmpty()) {
                List<int[]> nextLevel = new ArrayList<>();
                for (int[] lastLevel : queue) {
                    System.out.println(Arrays.toString(lastLevel));
                    if (lastLevel[0] == grid.length - 1 && lastLevel[1] == grid[0].length - 1) {
                        return step;
                    }
                    int obstacle = grid[lastLevel[0]][lastLevel[1]];
                    if (obstacle == 1 && lastLevel[2] <= 0) {
                        continue;
                    }
                    for (int[] d : dir) {
                        int nextI = lastLevel[0] + d[0];
                        int nextJ = lastLevel[1] + d[1];
                        if (nextJ >= 0 && nextI >= 0 && nextI < grid.length && nextJ < grid[0].length &&
                                (visited[nextI][nextJ] == -1 || (visited[nextI][nextJ] != -1 && (lastLevel[2] - obstacle > visited[nextI][nextJ])))) {
                            visited[nextI][nextJ] = lastLevel[2] - obstacle;
                            nextLevel.add(new int[]{nextI, nextJ, lastLevel[2] - obstacle});
                        }
                    }
                }
                queue = nextLevel;
                step++;
            }
            return -1;
        }
    }
}
