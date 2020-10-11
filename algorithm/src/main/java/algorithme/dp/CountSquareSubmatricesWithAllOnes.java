package algorithme.dp;

/**
 * 1277. Count Square Submatrices with All Ones
 * https://leetcode.com/problems/count-square-submatrices-with-all-ones/
 *
 * @author liudong
 */
public class CountSquareSubmatricesWithAllOnes {
    public static void main(String[] args) {
        String s1 = "abd";
        s1.intern();
        String s2 = "abd";
        System.out.println(s1 == s2);
    }

    class Solution {
        public int countSquares(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            int[][] dp = new int[m][n];
            int ans = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] != 0) {
                        int max = Math.min(i > 0 ? dp[i - 1][j] : 0, j > 0 ? dp[i][j - 1] : 0) + 1;
                        int tmp = 0;
                        for (int k = 0; k < max; k++) {
                            if (matrix[i - k][j - k] == 1) {
                                tmp++;
                            }
                        }
                        dp[i][j] = tmp;
                        ans += dp[i][j];
                    }
                }
            }
            return ans;
        }
    }
}
