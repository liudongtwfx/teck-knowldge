package algorithme.dp;

import java.util.Arrays;

/**
 * 1416. Restore The Array
 * https://leetcode.com/problems/restore-the-array/
 */
public class RestoreTheArray {
    public static void main(String[] args) {
        new RestoreTheArray().bridge();
    }

    private void bridge() {
        String s = "100000";
        int k = 1000000000;
        System.out.println(new Solution().numberOfArrays(s, k));
    }

    class Solution {
        public int numberOfArrays(String s, int k) {
            if (s.startsWith("0")) {
                return 0;
            }
            long[] dp = new long[s.length()];
            dp[0] = 1;
            for (int i = 1; i < s.length(); i++) {
                long count = 0;
                int j = i;
                for (; j > 0 && i - j < 10; j--) {
                    if (s.charAt(j) == '0') continue;
                    long num = parseLong(s.substring(j, i + 1));
                    if (num > k) {
                        break;
                    }
                    if (num > 0) {
                        count = (count + dp[j - 1]);
                    }
                }
                if (i < 11) {
                    long num = parseLong(s.substring(0, i + 1));
                    if (num > 0 && num <= k) {
                        count += 1;
                    }
                }
                dp[i] = count % (1000000007);
            }
            return (int) dp[s.length() - 1];
        }

        private long parseLong(String s) {
            if (s == null || s.isEmpty()) {
                return 0;
            }
            long ans = 0;
            for (char c : s.toCharArray()) {
                ans = ans * 10 + c - '0';
            }
            return ans;
        }
    }

    class Solution1 {
        public int numberOfArrays(String s, int k) {
            // dp[i] is number of ways to print valid arrays from string s start at i
            int[] dp = new int[s.length() + 1];
            int mod = (int) 1e9 + 7;
            Arrays.fill(dp, 0);
            dp[s.length()] = 1;

            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) == '0') {
                    continue;
                }
                long ans = 0, num = 0;
                for (int j = i; j < s.length(); j++) {
                    num = num * 10 + (s.charAt(j) - '0');
                    if (num > k)
                        break;
                    ans += dp[j + 1];
                    ans = ans % mod;
                }
                dp[i] = (int) ans;
            }
            return dp[0];
        }
    }
}
