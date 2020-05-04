package algorithme.dp;

/**
 * 221. Maximal Square
 * https://leetcode.com/problems/maximal-square/
 */
public class MaximalSquare {
    public static void main(String[] args) {
        new MaximalSquare().bridge();
    }

    private void bridge() {
        char[][] matrix = {{'1', '0', '1', '1', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        System.out.println(new Solution().maximalSquare(matrix));
    }

    class Solution {
        public int maximalSquare(char[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            int[][] dp = new int[m][n];
            int ans = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == '1') {
                        int k = Math.min(i > 0 ? dp[i - 1][j] : 0, j > 0 ? dp[i][j - 1] : 0) + 1;
                        for (int l = 0; l < k; l++) {
                            if (i >= k && j >= k && matrix[i - k][j - k] == '1') {
                                ans = Math.max(ans, k + 1);
                                dp[i][j] = k + 1;
                            }
                        }
                    }
                }
            }
            return ans * ans;
        }
    }
}
