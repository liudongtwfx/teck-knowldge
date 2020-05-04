package algorithme.dp;

/**
 * 1155. Number of Dice Rolls With Target Sum
 * https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/
 *
 * @author liudong
 */
public class NumberOfDiceRollsWithTargetSum {
    public static void main(String[] args) {
        new NumberOfDiceRollsWithTargetSum().bridge();
    }

    private void bridge() {
        int d = 30;
        int f = 30;
        int target = 500;
        System.out.println(new Solution().numRollsToTarget(d, f, target));
    }

    class Solution {
        public int numRollsToTarget(int d, int f, int target) {
            if (d * f < target || d > target) {
                return 0;
            }
            long[][] dp = new long[d + 1][target + 1];
            for (int i = 1; i <= f; i++) {
                dp[1][i] = 1;
            }
            for (int i = 1; i <= d; i++) {
                for (int k = i; k <= target; k++) {
                    for (int j = 1; j <= f; j++) {
                        if (k >= j) {
                            dp[i][k] = (dp[i][k] + dp[i - 1][k - j]) % ((int) 1e9 + 7);
                        }
                    }
                }
            }
            return (int) dp[d][target];
        }
    }
}
