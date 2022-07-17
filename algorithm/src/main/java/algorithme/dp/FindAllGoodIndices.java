package algorithme.dp;

import java.util.ArrayList;
import java.util.List;

public class FindAllGoodIndices {
    public static void main(String[] args) {
        new FindAllGoodIndices().bridge();
    }

    private void bridge() {
        int[] nums = {440043, 276285, 336957};
        Solution solution = new Solution();
        System.out.println(solution.goodIndices(nums, 1));
    }

    class Solution {
        public List<Integer> goodIndices(int[] nums, int k) {
            final int n = nums.length;
            int[] prefix = new int[n];
            prefix[0] = 1;
            for (int i = 1; i < n; i++) {
                if (nums[i] <= nums[i - 1]) {
                    prefix[i] = prefix[i - 1] + 1;
                } else {
                    prefix[i] = 1;
                }
            }

            int[] suffix = new int[n];
            suffix[n - 1] = 1;
            for (int i = n - 2; i >= 0; i--) {
                if (nums[i] <= nums[i + 1]) {
                    suffix[i] = suffix[i + 1] + 1;
                } else {
                    suffix[i] = 1;
                }
            }

            List<Integer> result = new ArrayList<>();
            for (int i = k; i < n - k; i++) {
                if (prefix[i - 1] >= k && suffix[i + 1] >= k) {
                    result.add(i);
                }
            }
            return result;
        }
    }
}
