package algorithme.dfs;

import algorithme.InputUtils;

import java.util.Arrays;

public class SnakesAndLadders909 {
    public static void main(String[] args) {
        new SnakesAndLadders909().bridge();
    }

    private void bridge() {
        String input = "[[-1,-1,30,14,15,-1],[23,9,-1,-1,-1,9],[12,5,7,24,-1,30],[10,-1,-1,-1,25,17],[32,-1,28,-1,-1,32],[-1,-1,23,-1,13,19]]";
        System.out.println(new Solution().snakesAndLadders(InputUtils.convertToArray(input)));
    }

    class Solution {
        private int[] cache;
        private boolean[] visited;

        public int snakesAndLadders(int[][] board) {
            int n = board.length;
            int[] valueBoard = getV(board);
            cache = new int[n * n + 1];
            visited = new boolean[n * n + 1];
            Arrays.fill(cache, Integer.MAX_VALUE);
            // System.out.println(Arrays.toString(valueBoard));
            int v = dfs(valueBoard, 1, n, 0, false);
            // return 1;
            System.out.println(Arrays.toString(cache));
            return v;
        }

        private int dfs(int[] valueBoard, int current, int n, int depth, boolean canTakeLadder) {
            if (cache[current] != Integer.MAX_VALUE) {
                return cache[current];
            }
            if (current == n * n) return 0;
            if (visited[current]) {
                return -1;
            }
            visited[current] = true;
            if (valueBoard[current] != -1 && canTakeLadder) {
                int v = dfs(valueBoard, valueBoard[current], n, depth + 1, false);
                cache[current] = v;
                visited[current] = false;
                return v;
            }
            int minValue = Integer.MAX_VALUE;
            for (int i = 1; i <= Math.min(6, n * n - current); i++) {
                int next = dfs(valueBoard, current + i, n, depth + 1, true);
                minValue = Math.min(minValue, next + 1);
            }
            cache[current] = minValue;
            visited[current] = false;
            return minValue;
        }

        private int[] getV(int[][] board) {
            int n = board.length;
            int[] boardNum = new int[n * n + 1];
            int i = n - 1, j = 0, d = 1;
            for (int k = 1; k <= n * n; k++) {
                boardNum[k] = board[i][j];
                if ((j == n - 1 && d == 1) || (j == 0 && d == -1)) {
                    i--;
                    d = -d;
                } else {
                    j += d;
                }
            }
            return boardNum;
        }
    }
}
