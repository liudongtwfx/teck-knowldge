package algorithme.search;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class WaysToSplitArrayIntoThreeSubarrays {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i < 99999; i++) {
            sum += i;
        }
        System.out.println(sum);
        new WaysToSplitArrayIntoThreeSubarrays().bridge();
    }

    private void bridge() {
        int[] nums = new int[5];
        int i = new Solution().waysToSplit(nums);
        System.out.println(i);
    }

    class Solution {
        public int waysToSplit(int[] nums) {
            NavigableMap<Integer, int[]> sumIndexMap = new TreeMap<>();
            int[] sumArr = new int[nums.length];
            int sum = 0, i = 0;
            while (i < nums.length) {
                sum += nums[i];
                sumArr[i] = sum;
                int j = i;
                while (j < nums.length && nums[j] == 0) {
                    j++;
                }
                int[] startAndEnd = new int[]{i - 1, j - 1};
                sumIndexMap.put(sum, startAndEnd);
                i = j;
            }
            long ans = 0;
            for (i = 0; i < nums.length; i++) {
                // mid >= left
                // sum[0:midEnd]-left>=left;
                Map.Entry<Integer, int[]> midStartWrapper = sumIndexMap.ceilingEntry(sumArr[i] * 2);
                long midStart = midStartWrapper == null ? Long.MAX_VALUE : midStartWrapper.getValue()[0] - 1;
                midStart = Math.max(midStart, i);
                // right >= mid
                // total-sum[0:midEnd] >= sum[o:midEnd]-left;
                // sum[0:midEnd]<= (total+left)/2;
                Map.Entry<Integer, int[]> midRightWrapper = sumIndexMap.floorEntry((sumArr[i] + sum) / 2);
                long midRight = midRightWrapper != null ? midRightWrapper.getValue()[1] : Long.MIN_VALUE;
                midRight = Math.min(midRight, nums.length - 2);
                if (midRight > midStart) {
                    System.out.println(ans);
                    ans = (ans + midRight - midStart) % 1_000_000_007;
                }
            }
            return (int) ans;
        }
    }
}
