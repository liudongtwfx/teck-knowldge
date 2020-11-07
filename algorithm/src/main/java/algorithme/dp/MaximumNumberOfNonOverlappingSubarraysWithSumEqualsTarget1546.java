package algorithme.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/maximum-number-of-non-overlapping-subarrays-with-sum-equals-target/
 */
public class MaximumNumberOfNonOverlappingSubarraysWithSumEqualsTarget1546 {
    public static void main(String[] args) {
        new MaximumNumberOfNonOverlappingSubarraysWithSumEqualsTarget1546().bridge();
    }


    private void bridge() {
        int[] nums = {3, 0, 2, 0, 2, 3, 3, 0, 0, 2, 1, 1, 1, 0, -1, -1, 1, -1, 1, 0, 2, 0, 0, 3, 0, 0, 3, 1, 0, 2, 0, -1, 2, -1, 1, 1, 3, 0, 2, 3, 3, 0, 0, 2, -1, 1};
        int target = 3;
        System.out.println(new Solution().maxNonOverlapping(nums, target));
    }

    class Solution {
        public int maxNonOverlapping(int[] nums, int target) {
            int[] dpMax = new int[nums.length];
            int max = 0, sum = 0;
            Map<Integer, Integer> sumIndexMap = new HashMap<>();
            sumIndexMap.put(0, -1);
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (sumIndexMap.containsKey(sum - target)) {
                    Integer preIndex = sumIndexMap.get(sum - target);
                    max = Math.max(max, preIndex >= 0 ? dpMax[preIndex] + 1 : 1);
                }
                dpMax[i] = max;
                sumIndexMap.put(sum, i);
            }
            return max;
        }
    }
}
