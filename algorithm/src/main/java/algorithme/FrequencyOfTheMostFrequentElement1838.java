package algorithme;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FrequencyOfTheMostFrequentElement1838 {
    public static void main(String[] args) {
        new FrequencyOfTheMostFrequentElement1838().bridge();
    }

    private void bridge() {
        int[] nums = {1, 2, 4};
        int i = new Solution().maxFrequency(nums, 5);
        System.out.println(i);
    }

    class Solution {
        private int k;
        private int[] nums;
        Set<Integer> conditionMatched = new HashSet<>();

        public int maxFrequency(int[] nums, int k) {
            this.nums = nums;
            this.k = k;
            int[] sums = new int[nums.length];
            int max = 1, sum = 0;
            Arrays.sort(nums);
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                sums[i] = sum;
                conditionMatched.clear();
                int last = binary(sums, 0, i, i);
                for (Integer integer : conditionMatched) {
                    max = Math.max(max, i - integer + 1);
                }
            }
            return max;
        }

        private int binary(final int[] sums, int start, int end, int index) {
            if (start > end) {
                return -1;
            }
            if (end - start <= 2) {
                for (int i = start; i <= end; i++) {
                    if (conditionMatch(sums, index, i)) {
                        conditionMatched.add(i);
                    }
                }
            }
            int half = (start + end) / 2;
            if (conditionMatch(sums, index, half)) {
                conditionMatched.add(half);
                return binary(sums, start, half - 1, index);
            }
            return binary(sums, half + 1, end, index);
        }

        private boolean conditionMatch(int[] sums, int index, int j) {
            if (j == 0) {
                return nums[index] * (index - j + 1) - sums[index] <= k;
            }
            return nums[index] * (index - j + 1) - sums[index] + sums[j - 1] <= k;
        }
    }
}
