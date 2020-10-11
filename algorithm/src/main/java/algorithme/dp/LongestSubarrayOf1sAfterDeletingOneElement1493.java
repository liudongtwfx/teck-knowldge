package algorithme.dp;


public class LongestSubarrayOf1sAfterDeletingOneElement1493 {
    public static void main(String[] args) {
    }


    class Solution {
        public int longestSubarray(int[] nums) {
            int len = nums.length;
            int[] rightMaxOnes = new int[len];
            int currentOnes = 0;
            for (int i = len - 1; i >= 0; i--) {
                rightMaxOnes[i] = currentOnes;
                if (nums[i] == 0) {
                    currentOnes = 0;
                } else {
                    currentOnes++;
                }
            }
            int ans = 0;
            currentOnes = 0;
            for (int i = 0; i < len; i++) {
                ans = Math.max(ans, currentOnes + rightMaxOnes[i]);
                if (nums[i] == 0) {
                    currentOnes = 0;
                } else {
                    currentOnes++;
                }
            }
            return ans;
        }
    }
}

