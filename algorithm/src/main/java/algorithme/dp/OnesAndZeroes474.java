package algorithme.dp;

public class OnesAndZeroes474 {
    public static void main(String[] args) {
        new OnesAndZeroes474().bridge();
    }

    private void bridge() {
        String[] str = {"0", "11", "1000", "01", "0", "101", "1", "1", "1", "0", "0", "0", "0", "1", "0", "0110101", "0", "11", "01", "00", "01111", "0011", "1", "1000", "0", "11101", "1", "0", "10", "0111"};
        System.out.println(new Solution().findMaxForm(str, 9, 80));
    }

    class Solution {
        public int findMaxForm(String[] strs, int m, int n) {
            int[][] dp = new int[m + 1][n + 1];
            for (String s : strs) {
                int zero = count(s);
                int one = s.length() - zero;
                for (int i = m; i >= zero; i--)
                    for (int j = n; j >= one; j--)
                        dp[i][j] = Math.max(1 + dp[i - zero][j - one], dp[i][j]);
            }
            return dp[m][n];
        }

        public int count(String str) {
            int zero = 0;
            for (char c : str.toCharArray())
                if (c == '0') zero++;
            return zero;
        }
    }
}
