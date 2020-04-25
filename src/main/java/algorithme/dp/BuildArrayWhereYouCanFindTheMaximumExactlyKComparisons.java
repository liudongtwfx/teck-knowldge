package algorithme.dp;

/**
 * 1420. Build Array Where You Can Find The Maximum Exactly K Comparisons
 * https://leetcode.com/problems/build-array-where-you-can-find-the-maximum-exactly-k-comparisons/
 */
public class BuildArrayWhereYouCanFindTheMaximumExactlyKComparisons {
    public static void main(String[] args) {
        new BuildArrayWhereYouCanFindTheMaximumExactlyKComparisons().bridge();
    }

    private void bridge() {
        int n = 3, m = 3, k = 1;
        int ans = new Solution().numOfArrays(n, m, k);
        System.out.println(ans);
    }

    class Solution {
        private final int MODE = (int) 1e9 + 7;

        public int numOfArrays(int n, int m, int k) {
            if (m < k) {
                return 0;
            }
            if (m == k) {
                return 1;
            }
            int[][][] dp = new int[n + 1][m + 1][k + 1];
            for (int i = 1; i <= m; i++) {
                dp[1][i][1] = 1;
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    for (int l = 1; l <= k; l++) {
                        dp[i][j][k] += (dp[i - 1][j][k]) * j;
                        for (int o = 1; o < j; o++) {
                            dp[i][j][k] += (dp[i - 1][o][k - 1]);
                        }
                    }
                }
            }
            return dp[n][m][k];
        }
    }
}
