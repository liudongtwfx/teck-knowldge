package algorithme.search;

import java.util.Arrays;

public class LongestSubsequenceWithLimitedSum2389 {
    public static void main(String[] args) {
        new LongestSubsequenceWithLimitedSum2389().bridge();
    }

    private void bridge() {
        int[] nums = {4, 5, 2, 1};
        int[] queries = {3, 10, 21};
        System.out.println(Arrays.toString(new Solution().answerQueries(nums, queries)));
    }

    class Solution {
        public int[] answerQueries(int[] nums, int[] queries) {
            int[] res = new int[queries.length];
            Arrays.sort(nums);
            for (int i = 0; i < res.length; i++) {
                res[i] = getRes(nums, queries[i]);
            }
            return res;
        }

        private int getRes(int[] nums, int sum) {
            int res = 0, count = 0;
            for (int num : nums) {
                if (res + num <= sum) {
                    res += num;
                    count++;
                } else return count;
            }
            return count;
        }
    }
}
