package algorithme.array;

public class SumOfBeautyInTheArray2021 {
    public static void main(String[] args) {
        new SumOfBeautyInTheArray2021().bridge();
    }

    private void bridge() {
        int[] nums = {5, 5, 10, 4, 10};
        System.out.println(new Solution().sumOfBeauties(nums));
    }

    class Solution {
        public int sumOfBeauties(int[] nums) {
            int[] minIndex = new int[nums.length];
            int min = Integer.MAX_VALUE;
            for (int i = nums.length - 1; i >= 0; i--) {
                min = Math.min(min, nums[i]);
                minIndex[i] = min;
            }
            int sum = 0;
            int max = nums[0];
            for (int i = 1; i < nums.length - 1; i++) {
                if (nums[i] > max && nums[i] < minIndex[i + 1]) {
                    sum += 2;
                } else if (nums[i - 1] < nums[i] && nums[i] < nums[i + 1]) {
                    sum++;
                }
                max = Math.max(max, nums[i]);
            }
            return sum;
        }
    }
}
