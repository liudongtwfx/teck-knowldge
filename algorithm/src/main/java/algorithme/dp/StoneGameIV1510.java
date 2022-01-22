package algorithme.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoneGameIV1510 {
    public static void main(String[] args) {
        new StoneGameIV1510().bridge();
    }

    private void bridge() {
        System.out.println(new Solution().winnerSquareGame(11));
    }

    class Solution {
        public boolean winnerSquareGame(int n) {
            List<Integer> squares = new ArrayList<>();
            for (int i = 1; i * i <= n; i++) {
                squares.add(i * i);
            }
            boolean[] dp = new boolean[n + 1];
            dp[0] = true;
            for (int i = 1; i <= n; i++) {
                for (Integer square : squares) {
                    if (i - square == 0) {
                        dp[i] = true;
                        break;
                    }
                    if (i < square) {
                        break;
                    }
                    if (!dp[i - square]) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            System.out.println(Arrays.toString(dp));
            return dp[n];
        }
    }
}
