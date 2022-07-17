package algorithme.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberOfPairsSatisfyingInequality2436 {
    public static void main(String[] args) {
        new NumberOfPairsSatisfyingInequality2436().bridge();
    }

    /**
     * [-5,-1,4,-1,-5,0,-4,5]
     * [4,-1,-2,-5,-4,2,3,1]
     * -5
     */
    private void bridge() {
        int[] nums1 = {3, 2, 5};
        int[] nums2 = {2, 2, 1};
        System.out.println(new Solution().numberOfPairs(nums1, nums2, 1));
    }

    class Solution {
        public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
            List<Integer> diffDp = new ArrayList<>();
            long ans = 0;
            for (int i = 0; i < nums1.length; i++) {
                int findPos = findPos(diffDp, nums1[i] - nums2[i]);
                ans += findPos;
                int val = nums1[i] - nums2[i] - diff;
                int insertPos = Collections.binarySearch(diffDp, val);
                if (insertPos < 0) {
                    insertPos = -(insertPos + 1);
                }
                diffDp.add(insertPos, nums1[i] - nums2[i] - diff);
            }
            return ans;
        }

        private int findPos(List<Integer> diffDp, int val) {
            int pos = Collections.binarySearch(diffDp, val);
            if (pos >= 0) {
                for (int i = pos; i < diffDp.size(); i++) {
                    if (diffDp.get(i) != val) {
                        return i;
                    }
                }
                return pos + 1;
            }
            return -(pos + 1);
        }
    }
}
