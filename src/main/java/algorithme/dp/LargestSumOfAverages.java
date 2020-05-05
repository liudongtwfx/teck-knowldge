package algorithme.dp;

/**
 * 813. Largest Sum of Averages
 * https://leetcode.com/problems/largest-sum-of-averages/
 *
 * @author liudong
 */
public class LargestSumOfAverages {
    public static void main(String[] args) {
        new LargestSumOfAverages().bridge();
    }

    private void bridge() {
        int[] A = {4, 1, 7, 5, 6, 2, 3};
        int K = 4;
        System.out.println(new Solution().largestSumOfAverages(A, K));
    }

    class Solution {
        public double largestSumOfAverages(int[] A, int K) {
            int len = A.length;
            double[][] dp = new double[len][len + 1];
            int sum = 0;
            for (int i = 0; i < len; i++) {
                sum += A[i];
                dp[i][i + 1] = sum;
                dp[i][1] = (double) sum / (i + 1);
                for (int j = 2; j <= Math.min(i, K); j++) {
                    for (int k = i; k >= 1; k--) {
                        dp[i][j] = Math.max(dp[i][j], dp[k - 1][j - 1] + (dp[i][i + 1] - dp[k - 1][k]) / (i - k + 1));
                    }
                }
            }
            return dp[len - 1][K];
        }
    }
}
