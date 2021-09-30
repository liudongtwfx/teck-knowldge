package algorithme.array;

import java.util.Arrays;

public class ArrayWithElementsNotEqualToAverageOfNeighbors {


    public static void main(String[] args) {
        new ArrayWithElementsNotEqualToAverageOfNeighbors().bridge();
    }

    private void bridge() {
        int[] nums = {0, 4, 1, 5, 3};
        System.out.println(Arrays.toString(new Solution().rearrangeArray(nums)));
    }

    class Solution {
        public int[] rearrangeArray(int[] nums) {
            Arrays.sort(nums);
            int n = nums.length - 1;
            for (int i = 1; i < nums.length - 1; i++) {

                int t = n;
                while (t >= 0 && nums[i] * 2 == nums[i + 1] + nums[i - 1]) {
                    System.out.println(Arrays.toString(nums));
                    int tmp = nums[i];
                    nums[i] = nums[t];
                    nums[t] = tmp;
                    t--;
                }
            }
            return nums;
        }
    }
}
