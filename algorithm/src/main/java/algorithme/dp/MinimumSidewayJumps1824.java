package algorithme.dp;

import java.util.Arrays;

public class MinimumSidewayJumps1824 {
    public static void main(String[] args) {
        new MinimumSidewayJumps1824().bridge();
    }

    private void bridge() {
        int[] obstacles = {0, 1, 2, 3, 0};
        int ans = new Solution().minSideJumps(obstacles);
        System.out.println(ans);
    }

    /**
     * [m,n]1，2，3哪个出现的次数最少
     * 0 0 0
     * 0 0 1
     * 0 1 0
     * 1 0 0
     * 0 0 0
     */
    class Solution {
        public int minSideJumps(int[] obstacles) {
            int length = obstacles.length;
            int[] dp = {0, 1, 0, 1};
            for (int i = 1; i < length; i++) {
                int[] last = Arrays.copyOf(dp, 4);
                System.out.println(Arrays.toString(last));
                for (int j = 1; j <= 3; j++) {
                    if (obstacles[i] != j) {
                        dp[j] = last[j];
                        for (int k = 1; k <= 3; k++) {
                            if (k != j) {
                                dp[j] = Math.min(dp[j], last[k] + (obstacles[i] == k ? 2 : 1));
                            }
                        }
                    } else {
                        dp[j] = i + 1;
                    }
                }
            }
            System.out.println(Arrays.toString(dp));
            return Math.min(Math.min(dp[1], dp[2]), dp[3]);
        }
    }
}
