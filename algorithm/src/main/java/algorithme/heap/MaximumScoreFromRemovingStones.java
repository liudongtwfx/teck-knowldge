package algorithme.heap;

import java.util.Arrays;

public class MaximumScoreFromRemovingStones {
    public static void main(String[] args) {
        new MaximumScoreFromRemovingStones().bridge();
    }

    private void bridge() {
        System.out.println(new Solution().maximumScore(4, 4, 7));
    }

    class Solution {
        public int maximumScore(int a, int b, int c) {
            int[] nums = new int[]{a, b, c};
            Arrays.sort(nums);
            if (nums[0] + nums[1] <= nums[2]) {
                return nums[0] + nums[1];
            }
            return (nums[0] + nums[1] - nums[2]) / 2 + nums[2];
        }
    }
}
