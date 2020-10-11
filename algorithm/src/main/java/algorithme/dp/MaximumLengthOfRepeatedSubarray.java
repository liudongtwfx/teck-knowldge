package algorithme.dp;

import java.util.Arrays;

/**
 * 718. Maximum Length of Repeated Subarray
 * https://leetcode.com/problems/maximum-length-of-repeated-subarray/
 */
public class MaximumLengthOfRepeatedSubarray {
    public static void main(String[] args) {

    }

    class Solution {
        public int findLength(int[] A, int[] B) {
            int m = A.length;
            int n = B.length;
            int ans = 0;
            int[] dp = new int[n];
            for (int i = 0; i < m; i++) {
                int[] last = Arrays.copyOf(dp, n);
                dp = new int[n];
                if (A[i] == B[0]) {
                    dp[0] = 1;
                }
                for (int j = 1; j < n; j++) {
                    if (A[i] == B[j]) {
                        dp[j] = last[j - 1] + 1;
                    }
                    ans = Math.max(ans, dp[j]);
                }
            }
            return ans;
        }
    }
}
