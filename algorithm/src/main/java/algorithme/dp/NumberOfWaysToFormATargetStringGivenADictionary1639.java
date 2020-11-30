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
        final int MOD = 1_000_000_007;

        public int numWays(String[] words, String target) {
            long[][] dp = new long[target.length()][words[0].length()];
            int[][] charDp = new int[words[0].length()][26];
            for (int i = 0; i < words.length; i++) {
                for (int j = 0; j < words[0].length(); j++) {
                    charDp[j][words[i].charAt(j) - 'a']++;
                }
            }
            for (int i = 0; i < words[0].length(); i++) {
                dp[0][i] = charDp[i][target.charAt(0) - 'a'];
                if (i > 0) {
                    dp[0][i] += dp[0][i - 1];
                }
            }
            for (int j = 1; j < words[0].length(); j++) {
                for (int i = 1; i < target.length(); i++) {
                    if (i > j) {
                        break;
                    }
                    int c = charDp[j][target.charAt(i) - 'a'];
                    dp[i][j] += dp[i][j - 1];
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - 1] * c) % MOD;
                }
            }
            return (int) dp[target.length() - 1][words[0].length() - 1];
        }
    }
}
