package algorithme.sort;

import java.util.*;

public class CountOfSmallerNumbersAfterSelf315 {
    public static void main(String[] args) {
        new CountOfSmallerNumbersAfterSelf315().bridge();
    }

    private void bridge() {
        int[] nums = {5, 2, 6, 1};
        System.out.println(new Solution().countSmaller(nums));
    }

    class Solution {
        private TreeMap<Integer, Integer> indexMap = new TreeMap<>();

        public List<Integer> countSmaller(int[] nums) {
            Integer[] ans = new Integer[nums.length];
            List<Integer> sortNums = new ArrayList<>();
            for (int i = nums.length - 1; i >= 0; i--) {
                int index = findIndex(sortNums, nums[i], 0, sortNums.size() - 1);
                ans[i] = index;
                sortNums.add(index, nums[i]);
                Map.Entry<Integer, Integer> entry = indexMap.higherEntry(nums[i]);
                indexMap.put(nums[i], entry != null ? entry.getValue() - 1 : 0);
            }
            return Arrays.asList(ans);
        }

        private int findIndex(List<Integer> nums, int num, int start, int end) {
            if (end - start <= 3) {
                for (int i = end; i >= start; i--) {
                    if (nums.get(i) < num) {
                        return i + 1;
                    }
                }
                return start;
            }
            int mid = (start + end) / 2;
            int value = nums.get(mid);
            if (value > num) {
                return findIndex(nums, num, start, mid - 1);
            }
            if (value < num) {
                return findIndex(nums, num, mid + 1, end);
            }
            Map.Entry<Integer, Integer> entry = indexMap.lowerEntry(num);
            return entry == null ? 0 : entry.getValue() + 1;
        }
    }
}
