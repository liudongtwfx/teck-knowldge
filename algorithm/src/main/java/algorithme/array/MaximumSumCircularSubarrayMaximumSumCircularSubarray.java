package algorithme.array;

/**
 * 918. Maximum Sum Circular Subarray
 * https://leetcode.com/problems/maximum-sum-circular-subarray/
 *
 * @author liudong
 */
public class MaximumSumCircularSubarrayMaximumSumCircularSubarray {
    public static void main(String[] args) {
        new MaximumSumCircularSubarrayMaximumSumCircularSubarray().bridge();
    }

    private void bridge() {
        int[] A = {5, -3, 5};
        System.out.println(new Solution().maxSubarraySumCircular(A));
    }


    /**
     * max_circular_sum = Max(max_non_circular_sum, sum - min_non_circular_sum);
     * A special case is sum == min_non_circular_sum.
     */
    class Solution {
        public int maxSubarraySumCircular(int[] A) {
            int max = globalValue(A, true);
            int min = globalValue(A, false);
            int sum = 0;
            for (int i : A) {
                sum += i;
            }
            return min == sum ? max : Math.max(max, sum - min);
        }


        // a helper function to get the non-circular maximum or minimum
        public int globalValue(int[] A, boolean max) {
            int global = A[0], local = A[0];

            for (int i = 1; i < A.length; i++) {
                if ((max && local < 0) || (!max && local > 0)) {
                    local = A[i];
                } else {
                    local += A[i];
                }
                if ((max && global < local) || (!max && global > local)) {
                    global = local;
                }
            }
            return global;
        }
    }
}
