package algorithme.array;

import java.util.Arrays;

public class MinimumDifferenceBetweenLargestAndSmallestValueInThreeMoves {
    public static void main(String[] args) {
        new MinimumDifferenceBetweenLargestAndSmallestValueInThreeMoves().bridge();
    }

    private void bridge() {
        int[] nums = {};
        System.out.println(new Solution().minDifference(nums));
    }

    class Solution {
        public int minDifference(int[] nums) {
            if (nums.length <= 4) {
                return 0;
            }
            Arrays.sort(nums);
            int ans = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                ans = Math.min(ans, Math.abs(nums[i] - nums[nums.length - 4 + i]));
                ans = Math.min(ans, Math.abs(nums[nums.length - 1 - i] - nums[3 - i]));
            }
            return ans;
        }
    }
}
