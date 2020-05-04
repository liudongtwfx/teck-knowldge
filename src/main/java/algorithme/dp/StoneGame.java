package algorithme.dp;

/**
 * 877. Stone Game
 * https://leetcode.com/problems/stone-game/
 *
 * @author liudong
 */
public class StoneGame {
    public static void main(String[] args) {
        new StoneGame().bridge();
    }

    private void bridge() {
        int[] piles = {5, 3, 4, 5};
        boolean b = new Solution().stoneGame(piles);
        System.out.println(b);
    }

    class Solution {
        private int[][] dp;

        public boolean stoneGame(int[] piles) {
            int sum = 0;
            for (int pile : piles) {
                sum += pile;
            }
            dp = new int[piles.length][piles.length];
            int min = dfs(piles, 0, piles.length - 1);
            System.out.println(min);
            return min > sum - min;
        }

        private int dfs(int[] piles, int start, int end) {
            if (dp[start][end] != 0) {
                return dp[start][end];
            }
            if (end < start) {
                return 0;
            }
            if (end == start) {
                return piles[end];
            }
            int min = Math.max(piles[start] + dfs(piles, start + 2, end), piles[start] + dfs(piles, start + 1, end - 1));
            int min2 = Math.max(piles[end] + dfs(piles, start, end - 2), piles[end] + dfs(piles, start + 1, end - 1));

            int ans = Math.max(min, min2);
            dp[start][end] = ans;
            return ans;
        }
    }
}
