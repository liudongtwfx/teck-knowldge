package algorithme.dp;

import java.util.Arrays;

/**
 * 416. Partition Equal Subset Sum
 */
public class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        new PartitionEqualSubsetSum().bridge();
    }

    private void bridge() {
        int[] nums = {1, 5, 11, 5};
        System.out.println(new Solution().canPartition(nums));
    }

    class Solution {
        public boolean canPartition(int[] nums) {
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            if (sum % 2 != 0) {
                return false;
            }
            int target = sum / 2;
            Arrays.sort(nums);
            boolean[] dp = new boolean[target + 1];
            for (int i = nums.length - 1; i >= 0; i--) {
                if (nums[i] > target) {
                    return false;
                }
                if (nums[i] == target) {
                    return true;
                }
                for (int j = target; j >= nums[i]; j--) {
                    if (dp[j - nums[i]]) {
                        dp[j] = true;
                    }
                }
            }
            return dp[target];
        }
    }
}
