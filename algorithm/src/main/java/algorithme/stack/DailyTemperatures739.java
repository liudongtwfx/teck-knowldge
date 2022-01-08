package algorithme.stack;

import java.util.Arrays;

public class DailyTemperatures739 {
    public static void main(String[] args) {
        new DailyTemperatures739().bridge();
    }

    private void bridge() {
        int[] temperated = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(new Solution().dailyTemperatures(temperated)));
    }

    class Solution {
        public int[] dailyTemperatures(int[] temperatures) {
            int length = temperatures.length;
            int[] ans = new int[length];
            int[] tmp = new int[101];
            for (int i = length - 1; i >= 0; i--) {
                tmp[temperatures[i]] = i;
                ans[i] = findLongest(tmp, temperatures[i], i);
            }
            return ans;
        }

        private int findLongest(int[] tmp, int temperature, int index) {
            int ans = Integer.MAX_VALUE;
            for (int i = tmp.length - 1; i >= 0; i--) {
                if (i <= temperature) {
                    return ans == Integer.MAX_VALUE ? 0 : ans;
                }
                if (tmp[i] > 0) {
                    ans = Math.min(ans, tmp[i] - index);
                }
            }
            return ans;
        }
    }
}
