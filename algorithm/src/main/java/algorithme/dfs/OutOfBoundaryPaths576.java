package algorithme.dfs;

public class OutOfBoundaryPaths576 {
    public static void main(String[] args) {
        new OutOfBoundaryPaths576().bridge();
    }

    private void bridge() {
        System.out.println(new Solution().findPaths(8, 7, 16, 5, 5));
    }

    class Solution {
        private int[][][] dp;
        private int mod = 1000000007;

        public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
            dp = new int[m][n][maxMove + 1];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < maxMove + 1; k++) {
                        dp[i][j][k] = -1;
                    }
                }
            }
            return dfs(m, n, maxMove, startRow, startColumn);
        }

        private int dfs(int m, int n, int maxMove, int startRow, int startColumn) {
            if (startRow >= m || startRow < 0 || startColumn >= n || startColumn < 0) {
                return 1;
            }
            if (maxMove <= 0) {
                return 0;
            }
            if (dp[startRow][startColumn][maxMove] != -1) {
                return dp[startRow][startColumn][maxMove];
            }
            long count = 0;
            count += dfs(m, n, maxMove - 1, startRow - 1, startColumn);
            count += dfs(m, n, maxMove - 1, startRow, startColumn + 1);
            count += dfs(m, n, maxMove - 1, startRow + 1, startColumn);
            count += dfs(m, n, maxMove - 1, startRow, startColumn - 1);
            dp[startRow][startColumn][maxMove] = (int) (count % mod);
            return (int) (count % mod);
        }
    }
}
