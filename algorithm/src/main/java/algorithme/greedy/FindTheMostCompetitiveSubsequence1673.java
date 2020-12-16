package algorithme.greedy;

import java.util.Arrays;

public class FindTheMostCompetitiveSubsequence1673 {
    public static void main(String[] args) {
        new FindTheMostCompetitiveSubsequence1673().bridge();
    }

    private void bridge() {
        int[] nums = {3, 5, 2, 6};
        int k = 2;
        System.out.println(new Solution().mostCompetitive(nums, k));
    }

    class Solution {
        public int[] mostCompetitive(int[] nums, int k) {
            int[] ans = new int[k + nums.length];
            int start = 0;
            //ans[start++]=nums[0];
            for (int i = 0; i < nums.length; i++) {
                while (start > 0 && ans[start - 1] > nums[i] && start + nums.length - i > k) {
                    start--;
                }
                if (start < k) {
                    ans[start++] = nums[i];
                }
            }
            return Arrays.copyOfRange(ans, 0, k);
        }
    }
}
