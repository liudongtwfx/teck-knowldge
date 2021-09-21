package algorithme.dp;

public class NumberOfWonderfulSubstrings1915 {
    public static void main(String[] args) {
        new NumberOfWonderfulSubstrings1915().bridge();
    }

    private void bridge() {
        String word = "aabb";
        long ans = new Solution().wonderfulSubstrings(word);
        System.out.println(ans);
    }

    class Solution {
        public long wonderfulSubstrings(String word) {
            long[] dp = new long[4096];
            char[] chars = word.toCharArray();
            int prefix = 0;
            long ans = 0;
            dp[0] = 1;
            for (int i = 0; i < chars.length; i++) {
                //ans++;
                int index = chars[i] - 'a';
                prefix ^= (1 << index);
                int j = 0;
                ans += dp[prefix];
                while (j <= 10) {
                    ans += dp[prefix ^ (1 << j)];
                    j++;
                }
                dp[prefix]++;
            }
            return ans;
        }
    }
}
