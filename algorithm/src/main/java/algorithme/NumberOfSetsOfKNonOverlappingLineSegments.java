package algorithme;

public class NumberOfSetsOfKNonOverlappingLineSegments {
    public static void main(String[] args) {
        new NumberOfSetsOfKNonOverlappingLineSegments().bridge();
    }

    private void bridge() {
        int n = 4;
        int k = 2;
        System.out.println(new Solution().numberOfSets(n, k));
    }

    class Solution {
        public int numberOfSets(int n, int k) {
            int[][] dp = new int[n + 1][n + 1];
            for (int i = 0; i < n; i++) {
                dp[i][1] = i;
            }
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= i; j++) {
                    for (int l = 0; l * 2 <= k && l <= i; l++) {
                        dp[i][j] += (dp[i][l] + dp[i - l][k - l]);
                    }
                }
            }
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= n; j++) {
                    System.out.print(dp[i][j] + " ");
                }
                System.out.println("\n");
            }
            return dp[n][k];
        }
    }
}
