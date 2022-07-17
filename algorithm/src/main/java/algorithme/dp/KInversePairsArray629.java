package algorithme.dp;

import java.lang.reflect.Array;
import java.util.Arrays;

public class KInversePairsArray629 {
    public static void main(String[] args) {
        new KInversePairsArray629().bridge();
    }

    private void bridge() {
        System.out.println(new Solution().kInversePairs(1000, 1000));
    }

    class Solution {
        private final int mod = 1000000007;

        public int kInversePairs(int n, int k) {
            long[][] dp = new long[n + 1][k + 1];
            for (int i = 1; i <= n; i++) {
                dp[i][0] = 1;
                int largest = i * (i - 1) / 2;
                for (int j = 1; j <= Math.min(k, largest); j++) {
                    if (j < i) {
                        dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % mod;
                    } else {
                        dp[i][j] = (dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - i] + mod) % mod;
                    }
                }
            }
            return (int) dp[n][k];
        }
    }
}
