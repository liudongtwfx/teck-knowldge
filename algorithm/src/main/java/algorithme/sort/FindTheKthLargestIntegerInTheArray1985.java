package algorithme.sort;

import java.util.Arrays;

public class FindTheKthLargestIntegerInTheArray1985 {
    public static void main(String[] args) {

    }


    class Solution {
        public String kthLargestNumber(String[] nums, int k) {
            Arrays.sort(nums, (o1, o2) -> {
                if (o1.length() < o2.length()) {
                    return 1;
                }
                if (o1.length() > o2.length()) {
                    return -1;
                }
                return o2.compareTo(o1);
            });
            return nums[k];
        }
    }
}
