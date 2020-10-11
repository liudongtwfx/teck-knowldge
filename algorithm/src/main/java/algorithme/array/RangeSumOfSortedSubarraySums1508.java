package algorithme.array;

import java.util.LinkedList;
import java.util.List;

public class RangeSumOfSortedSubarraySums1508 {
    public static void main(String[] args) {
        new RangeSumOfSortedSubarraySums1508().bridge();
    }

    private void bridge() {
        int[] nums = {1, 2, 3, 4};
        int n = 4, left = 1, right = 5;
        System.out.println(new Solution().rangeSum(nums, n, left, right));
    }

    class Solution {

        public int rangeSum(int[] nums, int n, int left, int right) {
            List<Integer> cur = new LinkedList<>();
            List<Integer> res = new LinkedList<>();
            for (int i = nums.length - 1; i >= 0; i--) {
                int finalI = i;
                cur.replaceAll(a -> a + nums[finalI]);
                res.add(nums[i]);
                res.addAll(cur);
                cur.add(nums[i]);
            }
            res.sort(Integer::compareTo);
            long ans = 0;
            return (int) res.subList(left - 1, right).stream().mapToLong(Long::valueOf).reduce(Long::sum).orElse(0);
        }
    }
}
