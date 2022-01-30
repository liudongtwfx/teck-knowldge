package algorithme.array;

public class RotateArray189 {
    public static void main(String[] args) {

    }

    class Solution {
        public void rotate(int[] nums, int k) {
            reverse(nums, 0, nums.length);
            reverse(nums, 0, k);
            reverse(nums, k + 1, nums.length);
        }

        private void reverse(int[] nums, int start, int end) {
            for (int i = start, j = end; i < j; i++, j--) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }
    }
}
