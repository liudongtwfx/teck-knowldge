package algorithme;

public class MaximumAbsoluteSumOfAnySubarray1749 {
    public static void main(String[] args) {
        new MaximumAbsoluteSumOfAnySubarray1749().bridge();
    }

    private void bridge() {
        int[] nums = {1, -3, 2, 3, -4};
        System.out.println(new Solution().maxAbsoluteSum(nums));
        int[] nums1 = {2, -5, 1, -4, 3, -2};
        System.out.println(new Solution().maxAbsoluteSum(nums1));

        int[] nums2 = {-3, -5, -3, -2, -6, 3, 10, -10, -8, -3, 0, 10, 3, -5, 8, 7, -9, -9, 5, -8};
        System.out.println(new Solution().maxAbsoluteSum(nums2));
    }

    class Solution {
        public int maxAbsoluteSum(int[] nums) {
            int ans1 = 0, tmp = 0;
            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];
                if (tmp + num >= num) {
                    tmp += num;
                } else {
                    tmp = num;
                }
                ans1 = Math.max(ans1, tmp);
                // System.out.println(ans1);
            }
            int ans2 = 0;
            tmp = 0;
            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];
                if (tmp + num <= num) {
                    tmp += num;
                } else {
                    tmp = num;
                }
                ans2 = Math.min(ans2, tmp);
                //System.out.println(ans);
            }
            return Math.max(Math.abs(ans1), Math.abs(ans2));
        }
    }
}
