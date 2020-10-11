package algorithme.dp;

/**
 * 943. Find the Shortest Superstring
 * https://leetcode.com/problems/find-the-shortest-superstring/
 *
 * @author liudong
 */
public class FindtheShortestSuperstring {

    public static void main(String[] args) {
        new FindtheShortestSuperstring().bridge();
    }

    private void bridge() {
        String[] a = {"bzdrk", "aps", "sntxb", "psntx"};
        System.out.println(new Solution().shortestSuperstring(a));
    }

    class Solution {
        private String s;

        public String shortestSuperstring(String[] A) {
            String[][] dp = new String[A.length][A.length];
            for (int i = 0; i < A.length; i++) {
                dp[i][i] = A[i];
            }
            for (int i = 0; i < A.length; i++) {
                for (int j = i - 1; j >= 0; j--) {
                    // dp[i][j], i->j 的 最短字符串 ;
                    dp[j][i] = dp[j][i - 1] + A[i];
                    for (int k = j; k < i; k++) {
                        //A[i] + dp[0][k] + dp[k][j] 三部分 ;
                        String tmp1 = min(min(A[i], dp[j][k]), dp[k + 1][i - 1]);
                        dp[j][i] = minLen(tmp1, dp[j][i]);
                    }
                }
            }
            return dp[0][A.length - 1];
        }

        private String minLen(String tmp1, String s) {
            if (tmp1.length() < s.length()) {
                return tmp1;
            }
            return s;
        }

        private String min(String left, String right) {
            String s1 = add(left, right);
            String s2 = add(right, left);
            return s1.length() < s2.length() ? s1 : s2;
        }

        private String add(String left, String right) {
            if (left == null) {
                return right == null ? "" : right;
            }
            if (right == null) {
                return left;
            }

            for (int i = right.length(); i > 0; i--) {
                if (left.endsWith(right.substring(0, i))) {
                    return left + right.substring(i);
                }
            }
            return left + right;
        }
    }
}
