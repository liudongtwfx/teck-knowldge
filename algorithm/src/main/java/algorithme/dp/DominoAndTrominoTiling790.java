package algorithme.dp;

public class DominoAndTrominoTiling790 {
    public static void main(String[] args) {
        new DominoAndTrominoTiling790().bridge();
    }

    private void bridge() {
        System.out.println(new Solution().numTilings(4));
    }

    class Solution {
        public int numTilings(int n) {
            if (n <= 2) return n;
            if (n == 3) return 5;
            int[] dp = new int[n + 1];
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 5;
            for (int i = 4; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3] * 2;
            }
            return dp[n];
        }
    }
}
