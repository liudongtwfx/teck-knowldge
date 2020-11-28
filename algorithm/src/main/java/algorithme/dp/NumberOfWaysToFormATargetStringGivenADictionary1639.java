package algorithme.dp;

public class NumberOfWaysToFormATargetStringGivenADictionary1639 {
    public static void main(String[] args) {
        new NumberOfWaysToFormATargetStringGivenADictionary1639().bridge();
    }

    private void bridge() {
        String[] words = {"acca", "bbbb", "caca"};
        String target = "aba";
        int ans = new Solution().numWays(words, target);
        System.out.println(ans);
    }

    class Solution {
        public int numWays(String[] words, String target) {
            long[][] dp = new long[target.length()][words[0].length()];
            int[][] charDp = new int[words[0].length()][26];
            for (int i = 0; i < words.length; i++) {
                for (int j = 0; j < words[0].length(); j++) {
                    charDp[j][words[i].charAt(j) - 'a']++;
                }
            }
            final int MOD = 1_000_000_007;
            for (int j = 0; j < words[0].length(); j++) {
                for (int i = 0; i < target.length(); i++) {
                    int c = charDp[j][target.charAt(i) - 'a'];
                    if (i == 0) {
                        dp[i][j] = c;
                    }
                    if (j > 0) {
                        dp[i][j] = (dp[i][j] + dp[i][j - 1]) % MOD;
                    }
                    if (i > 0 && j > 0) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - 1] * c) % MOD;
                    }
                }
            }
            return (int) dp[target.length() - 1][words[0].length() - 1];
        }
    }
}
