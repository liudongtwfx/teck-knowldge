package algorithme.dp;

/**
 * 1130. Minimum Cost Tree From Leaf Values
 * https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/
 *
 * @author liudong
 */
public class MinimumCostTreeFromLeafValues {
    public static void main(String[] args) {
        new MinimumCostTreeFromLeafValues().bridge();
    }

    private void bridge() {
        int[] arr = {6, 2, 4, 3};
        System.out.println(new Solution().mctFromLeafValues(arr));
    }

    class Solution {

        public int mctFromLeafValues(int[] arr) {
            int len = arr.length;
            int[][] dp = new int[len + 1][len + 1];
            int[][] maxValueDp = new int[len + 1][len + 1];
            for (int i = 0; i < len; i++) {
                int max = arr[i];
                for (int j = i; j >= 0; j--) {
                    max = Math.max(max, arr[j]);
                    maxValueDp[j][i] = max;
                }
            }
            for (int distance = 1; distance <= len; distance++) {
                for (int start = 0; start + distance < len; start++) {
                    int end = start + distance;
                    dp[start][end] = Integer.MAX_VALUE;
                    for (int i = start; i < end; i++) {
                        dp[start][end] = Math.min(dp[start][end], maxValueDp[start][i] * maxValueDp[i + 1][end] + dp[start][i] + dp[i + 1][end]);
                    }
                }
            }
            return dp[0][len - 1];
        }
    }
}
