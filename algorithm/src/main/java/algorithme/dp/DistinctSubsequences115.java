package algorithme.dp;

import java.util.Arrays;

public class DistinctSubsequences115 {
    public static void main(String[] args) {
        new DistinctSubsequences115().bridge();
    }


    private void bridge() {
        // "rabbbit"
        //"rabbit"
        String s = "aabb";
        String t = "abb";
        System.out.println(new Solution().numDistinct(s, t));
    }


    class Solution {
        public int numDistinct(String s, String t) {
            int sLength = s.length();
            int tLength = t.length();
            int[] dp = new int[sLength + 1];
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == t.charAt(0)) {
                    dp[i + 1] = dp[i] + 1;
                } else {
                    dp[i + 1] = dp[i];
                }
            }
            for (int j = 2; j <= tLength; j++) {
                int[] last = Arrays.copyOf(dp, dp.length);
                Arrays.fill(dp, 0);
                for (int i = j; i <= sLength; i++) {
                    if (s.charAt(i - 1) == t.charAt(j - 1)) {
                        dp[i] = last[i - 1] + dp[i - 1];
                    } else {
                        dp[i] = dp[i - 1];
                    }
                }
            }
            return dp[sLength];
        }
    }
}
