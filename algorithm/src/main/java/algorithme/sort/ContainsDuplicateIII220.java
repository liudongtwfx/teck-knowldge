package algorithme.sort;

import java.util.TreeMap;

public class ContainsDuplicateIII220 {
    public static void main(String[] args) {
        new ContainsDuplicateIII220().bridge();
    }

    private void bridge() {
        int[] nums = {1, 2};
        int k = 0;
        int t = 1;
        System.out.println(new Solution().containsNearbyAlmostDuplicate(nums, k, t));
        
    }

    class Solution {
        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            if (nums.length == 0 || (k == 0)) {
                return false;
            }

            TreeMap<Long, Integer> map = new TreeMap<>();
            // num-t<=value<=num+t
            map.put((long) nums[0], 0);
            for (int i = 1; i < nums.length; i++) {
                long min = (long) nums[i] - t;
                long max = (long) nums[i] + t;
                Long low = map.ceilingKey(min);
                Long high = map.floorKey(max);
                if (low != null && high != null && low <= high) {
                    return true;
                }
                int shouldPollIndex = i - k;
                if (shouldPollIndex >= 0) {
                    Integer shouldPollValue = map.get((long) nums[shouldPollIndex]);
                    if (shouldPollValue == shouldPollIndex) {
                        map.remove((long) nums[shouldPollIndex]);
                    }
                }
                map.put((long) nums[i], i);
            }
            return false;
        }
    }
}
