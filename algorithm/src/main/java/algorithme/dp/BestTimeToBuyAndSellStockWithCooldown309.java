package algorithme.dp;

import java.util.Arrays;

public class BestTimeToBuyAndSellStockWithCooldown309 {
    public static void main(String[] args) {
        new BestTimeToBuyAndSellStockWithCooldown309().bridge();
    }

    private void bridge() {
        int[] prices = {4, 1, 2};
        System.out.println(prices.length);
        System.out.println(new Solution().maxProfit(prices));
    }

    class Solution {
        public int maxProfit(int[] prices) {
            int N = prices.length;
            int[] dp = new int[N];
            for (int i = N - 1; i >= 0; i--) {
                int min = Integer.MAX_VALUE;
                int maxValue = 0;
                int tmp = 0;
                for (int j = i; j < N; j++) {
                    maxValue = Math.max(maxValue, prices[j]);
                    tmp = Math.max(tmp, maxValue - min);
                    if (prices[j] < min) {
                        min = prices[j];
                        maxValue = prices[j];
                    }
                    dp[i] = Math.max(tmp, dp[i]);
                    if (j < N - 2) {
                        dp[i] = Math.max(dp[i], tmp + dp[j + 2]);
                    }
                }
            }
            System.out.println(Arrays.toString(dp));
            return dp[0];
        }
    }
}
