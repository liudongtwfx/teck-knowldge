package algorithme.greedy;

import java.util.Arrays;

public class MaximumElementAfterDecreasingAndRearranging1864 {
    public static void main(String[] args) {

    }

    class Solution {
        public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
            Arrays.sort(arr);
            int max = 0;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] - arr[i - 1] <= 1) {
                    max = arr[i];
                    continue;
                }
                arr[i] = arr[i - 1] + 1;
                max = arr[i];
            }
            return max;
        }
    }
}
