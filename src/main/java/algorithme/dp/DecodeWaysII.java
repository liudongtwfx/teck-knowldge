package algorithme.dp;

/**
 * 639. Decode Ways II
 * https://leetcode.com/problems/decode-ways-ii/
 *
 * @author liudong
 */
public class DecodeWaysII {
    public static void main(String[] args) {
        new DecodeWaysII().bridge();
    }

    private void bridge() {
        String s = "*1";
        System.out.println(new Solution().numDecodings(s));
    }

    class Solution {
        private final int MODE = (int) 1e9 + 7;


        public int numDecodings(String s) {
            int length = s.length();
            long[] dp = new long[length];
            for (int i = 0; i < length; i++) {
                char c = s.charAt(i);
                if (c == '*') {
                    dp[i] = ((i > 0 ? dp[i - 1] : 1) * 9) % MODE;
                } else if (c != '0') {
                    dp[i] = i > 0 ? dp[i - 1] : 1;
                }
                if (i > 0) {
                    dp[i] = (dp[i] + (i > 1 ? dp[i - 2] : 1) * getLastTwo(s.charAt(i - 1), c)) % (MODE);
                }
            }
            return (int) dp[length - 1];
        }


        private int getLastTwo(char first, char second) {
            if (first == '0') {
                return 0;
            }
            if (first == '*' && second == '*') {
                return 15;
            }
            if (first == '*') {
                return second <= '6' ? 2 : 1;
            }
            if (second == '*') {
                return first == '1' ? 9 : first == '2' ? 6 : 0;
            }
            return (first - '0') * 10 + (second - '0') <= 26 ? 1 : 0;
        }
    }
}
