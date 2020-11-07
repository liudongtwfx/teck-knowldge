package algorithme.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NumberOfSubsequencesThatSatisfyTheGivenSumCondition1498 {
    public static void main(String[] args) {
        System.out.println(4 << 1);
        System.out.println(4 >> 1);
        new NumberOfSubsequencesThatSatisfyTheGivenSumCondition1498().bridge();
    }

    private void bridge() {
        int[] nums = {3, 5, 6, 7};
        int target = 9;
        int ans = new Solution().numSubseq(nums, target);
        System.out.println(ans);
    }

    class Solution {
        private static final int MODEL = 1000000000 + 7;
        private final Map<Integer, Integer> CACHE = new HashMap<>();

        public int numSubseq(int[] nums, int target) {
            Arrays.sort(nums);
            int i = 0, j = nums.length - 1;
            long ans = 0;
            while (i <= j) {
                while (nums[i] + nums[j] > target && i < j) {
                    j--;
                }
                ans = (ans + (getNums(j - i))) % MODEL;
                if (nums[i] * 2 <= target) {
                    ans++;
                }
                i++;
            }
            return (int) ans;
        }

        private int getNums(int count) {
            if (CACHE.containsKey(count)) {
                return CACHE.get(count);
            }
            long ans = 1;
            for (int i = 1; i <= count; i++) {
                ans = (ans << 1) % MODEL;
                CACHE.put(i, (int) ans - 1);
            }
            return ((int) ans) - 1;
        }
    }
}
