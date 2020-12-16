package algorithme.dp;

import java.util.Arrays;

public class NumberOfSetsOfKNonOverlappingLineSegments {
    public static void main(String[] args) {
        new NumberOfSetsOfKNonOverlappingLineSegments().bridge();
    }

    private void bridge() {
        int n = 1000;
        int k = 753;
        long start = System.currentTimeMillis();
        System.out.println(new Solution().numberOfSets(n, k));
        System.out.println(System.currentTimeMillis() - start);
    }

    class Solution {

        public int numberOfSets(int n, int k) {
            int[] dp = new int[n];
            Arrays.fill(dp, 1);
            int mod = 1_000_000_007;
            for (int i = 1; i <= k; i++) {
                int sum = dp[0];
                dp[0] = 0;
                for (int j = 1; j < n; j++) {
                    int prev = dp[j];
                    dp[j] = (sum + dp[j - 1]) % mod;
                    sum = (sum + prev) % mod;
                }
            }
            return dp[n - 1];
        }
    }
}
