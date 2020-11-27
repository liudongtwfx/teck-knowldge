package algorithme.array;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author liudong17
 */
public class CountOfRangeSum {
    public static void main(String[] args) {
        new CountOfRangeSum().bridge();
    }


    private void bridge() {
        int[] nums = {-2, 5, -1};
        int lower = -2;
        int upper = 2;
        System.out.println(new Solution().countRangeSum(nums, lower, upper));
    }

    class Solution {
        public int countRangeSum(int[] nums, int lower, int upper) {
            TreeMap<Long, Integer> sumCountMap = new TreeMap<>();
            int sum = 0;
            int count = 0;
            sumCountMap.put(0L, 1);
            for (int num : nums) {
                sum += num;
                Integer currentCount = sumCountMap.getOrDefault((long) sum, 0);
                int min = sum - upper;
                int max = sum - lower;
                Map<Long, Integer> integerIntegerNavigableMap = sumCountMap.subMap((long) min, true, (long) max, true);
                int c = integerIntegerNavigableMap.values().stream().mapToInt(Integer::valueOf).sum();
                count += c;
                sumCountMap.put((long) sum, currentCount + 1);
            }
            return count;
        }
    }

    class Solution1 {
        public int countRangeSum(int[] nums, int lower, int upper) {
            int dp = 0, n = 0;
            for (int i = 0; i < nums.length; i++) {
                for (int j = i; j < nums.length; j++) {
                    dp = j == i ? nums[i] : dp + nums[j];
                    if (dp >= lower && dp <= upper) {
                        n++;
                    }

                }
            }
            return n;
        }
    }

}
