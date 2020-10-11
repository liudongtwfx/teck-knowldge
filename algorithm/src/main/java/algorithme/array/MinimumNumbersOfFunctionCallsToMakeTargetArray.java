package algorithme.array;

public class MinimumNumbersOfFunctionCallsToMakeTargetArray {
    public static void main(String[] args) {
        System.out.println("true");
    }

    class Solution {
        public int minOperations(int[] nums) {
            int count = 0;
            boolean halve = false;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] % 2 == 1) {
                    count++;
                    --nums[i];
                }
                if (nums[i] > 0) {
                    halve = true;
                    nums[i] = nums[i] >> 1;
                }
            }
            if (halve) {
                ++count;
            }
            return count > 0 ? count + minOperations(nums) : 0;
        }
    }
}
