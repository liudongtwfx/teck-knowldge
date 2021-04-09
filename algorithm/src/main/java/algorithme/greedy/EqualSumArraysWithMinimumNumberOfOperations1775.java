package algorithme.greedy;

import java.util.Arrays;

public class EqualSumArraysWithMinimumNumberOfOperations1775 {
    public static void main(String[] args) {
        new EqualSumArraysWithMinimumNumberOfOperations1775().bridge();
    }

    private void bridge() {
        // [5,2,1,5,2,2,2,2,4,3,3,5]
        //[1,4,5,5,6,3,1,3,3]
        int[] nums1 = {5, 6, 4, 3, 1, 2};
        int[] nums2 = {6, 3, 3, 1, 4, 5, 3, 4, 1, 3, 4};
        System.out.println(new Solution().minOperations(nums1, nums2));
    }

    class Solution {
        public int minOperations(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int sum1 = Arrays.stream(nums1).sum();
            int sum2 = Arrays.stream(nums2).sum();
            if (sum1 > sum2) {
                return doAnswer(nums1, nums2, sum1, sum2);
            }
            if (sum1 < sum2) {
                return doAnswer(nums2, nums1, sum2, sum1);
            }
            return 0;
        }

        private int doAnswer(int[] maxNums, int[] minNums, int max, int min) {
            int maxIndex = maxNums.length - 1;
            int minIndex = 0;
            int ans = 0;
            while (maxIndex >= 0 && minIndex < minNums.length) {
                if (max == min) {
                    return ans;
                }
                int maxDiff = maxNums[maxIndex] - 1;
                int minDiff = 6 - minNums[minIndex];
                if (maxDiff >= minDiff) {
                    if (maxDiff >= max - min) {
                        return ans + 1;
                    }
                    max -= maxDiff;
                    maxIndex--;
                    ans += maxDiff == 0 ? 0 : 1;
                } else {
                    if (minDiff >= max - min) {
                        return ans + 1;
                    }
                    min += minDiff;
                    minIndex++;
                    ans += minDiff == 0 ? 0 : 1;
                }
            }
            while (maxIndex >= 0) {
                if (max == min) {
                    return ans;
                }
                int diff = (maxNums[maxIndex--] - 1);
                if (diff >= max - min) {
                    return ans + 1;
                }
                max -= diff;
                ans += diff == 0 ? 0 : 1;
            }
            while (minIndex < minNums.length) {
                if (max == min) {
                    return ans;
                }
                int diff = (6 - minNums[minIndex++]);
                if (diff >= max - min) {
                    return ans + 1;
                }
                min += diff;
                ans += diff == 0 ? 0 : 1;
            }
            if (max == min) {
                return ans;
            }
            return -1;
        }
    }
}
