package algorithme.dp;

import java.util.Arrays;

/**
 * 698. Partition to K Equal Sum Subsets
 * https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
 */
public class PartitionToKEqualSumSubsets {
    public static void main(String[] args) {
        new PartitionToKEqualSumSubsets().bridge();
    }

    private void bridge() {
        int[] nums = {3522, 181, 521, 515, 304, 123, 2512, 312, 922, 407, 146, 1932, 4037, 2646, 3871, 269};
        int k = 5;
        System.out.println(new Solution().canPartitionKSubsets(nums, k));
    }

    class Solution {
        public boolean canPartitionKSubsets(int[] nums, int k) {
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            if (sum % k != 0) {
                return false;
            }
            Arrays.sort(nums);
            return dfs(nums, new boolean[nums.length], sum / k, k, 0);
        }

        private boolean dfs(int[] nums, boolean[] visited, int target, int k, int current) {
            if (current > target) {
                return false;
            }
            if (target == current) {
                if (k == 1) {
                    return true;
                }
                return dfs(nums, visited, target, k - 1, 0);
            }
            for (int i = nums.length - 1; i >= 0; i--) {
                if (!visited[i]) {
                    if (nums[i] <= target) {
                        visited[i] = true;
                        if (dfs(nums, visited, target, k, current + nums[i])) {
                            return true;
                        }
                        visited[i] = false;
                    } else {
                        return false;
                    }
                }
            }
            return false;
        }
    }
}
