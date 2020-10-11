package algorithme.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FormLargestIntegerWithDigitsThatAddUpToTarget {
    public static void main(String[] args) {
        new FormLargestIntegerWithDigitsThatAddUpToTarget().bridge();

        String a = "75555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555";
        String b = "9777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777";
    }

    private void bridge() {
        int[] cost = {5, 3, 4, 4, 2, 5, 3, 5, 5};
        int target = 10;
        System.out.println(new Solution().largestNumber(cost, target));
    }

    class Solution {
        private String ans = "0";

        public String largestNumber(int[] cost, int target) {
            Map<Integer, Integer> valueIndexMap = new HashMap<>();
            String[] dp = new String[5001];
            Arrays.fill(dp, "");
            for (int i = 0; i < cost.length; i++) {
                Integer index = valueIndexMap.get(cost[i]);
                if (index == null || index < i + 1) {
                    valueIndexMap.put(cost[i], i + 1);
                    dp[cost[i]] = String.valueOf(i + 1);
                }
            }

            for (int i = 1; i <= target; i++) {
                String tmp = dp[i];
                for (Integer integer : valueIndexMap.keySet()) {
                    if (integer < i && !dp[i - integer].equals("")) {
                        tmp = maxStr(tmp, dp[i - integer] + valueIndexMap.get(integer));
                        tmp = maxStr(tmp, valueIndexMap.get(integer) + dp[i - integer]);
                    }
                }
                dp[i] = tmp;
            }
            return dp[target].equals("") ? "0" : dp[target];
        }

        private void dfs(Map<Integer, Integer> valueIndexMap, int target, String current) {
            if (target == 0) {
                ans = maxStr(ans, current);
                return;
            }
            if (target < 0) {
                return;
            }
            valueIndexMap.forEach((v, index) -> dfs(valueIndexMap, target - v, current + index));
        }

        private String maxStr(String a, String b) {
            if (a.length() > b.length()) {
                return a;
            }
            if (a.length() < b.length()) {
                return b;
            }
            for (int i = 0; i < a.length(); i++) {
                if (a.charAt(i) < b.charAt(i)) {
                    return b;
                }
                if (a.charAt(i) > b.charAt(i)) {
                    return a;
                }
            }
            return a;
        }
    }
}
