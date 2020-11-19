package algorithme;

import java.util.HashMap;
import java.util.Map;

public class MinimumOperationsToReduceXToZero1658 {
    public static void main(String[] args) {

    }

    class Solution {
        public int minOperations(int[] nums, int x) {
            Map<Integer, Integer> right = new HashMap<>();
            right.put(0, 0);
            int sum = 0;
            for (int i = nums.length - 1; i >= 0; i--) {
                sum += nums[i];
                right.put(sum, nums.length - i);
            }
            int res = right.getOrDefault(x, Integer.MAX_VALUE);
            sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (right.containsKey(x - sum) && right.get(x - sum) + i + 1 < nums.length) {
                    res = Math.min(res, right.get(x - sum) + i + 1);
                }
            }
            return res == Integer.MAX_VALUE ? -1 : res;
        }
    }
}
