package algorithme.dp;

import java.util.Arrays;

/**
 * 1363. Largest Multiple of Three
 * https://leetcode.com/problems/largest-multiple-of-three/
 */
public class LargestMultipleOfThree {
    public static void main(String[] args) {
        new LargestMultipleOfThree().bridge();
    }

    private void bridge() {
        int[] digits = {1, 1, 1, 2};
        System.out.println(new Solution().largestMultipleOfThree(digits));
    }

    class Solution {
        public String largestMultipleOfThree(int[] digits) {
            String[] cache = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
            int length = digits.length;
            Arrays.sort(digits);
            String[] dp = new String[]{"", "", ""};
            dp[digits[length - 1] % 3] = String.valueOf(digits[length - 1]);
            for (int i = length - 2; i >= 0; i--) {
                String[] last = dp;
                dp = new String[]{"", "", ""};
                dp[digits[i] % 3] = cache[digits[i]];
                for (int j = 0; j < 3; j++) {
                    dp[j] = compare(last[j], dp[j]);
                    String lastStr = last[(j + 3 - digits[i] % 3) % 3];
                    if (!"0".equals(lastStr) && !"".equals(lastStr)) {
                        dp[j] = compare(dp[j], lastStr + digits[i]);
                    }
                }
            }
            return dp[0];
        }


        private String compare(String a, String b) {
            if (a.length() == b.length()) {
                return a;
            }
            return a.length() > b.length() ? a : b;
        }
    }
}
