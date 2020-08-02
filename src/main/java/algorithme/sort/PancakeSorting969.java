package algorithme.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/pancake-sorting/
 * <p>
 *
 * </p>
 * Given an array A, we can perform a pancake flip: We choose some positive integer k <= A.length, then reverse the order of the first k elements of A.  We want to perform zero or more pancake flips (doing them one after another in succession) to sort the array A.
 * <p>
 * Return the k-values corresponding to a sequence of pancake flips that sort A.  Any valid answer that sorts the array within 10 * A.length flips will be judged as correct.
 */
public class PancakeSorting969 {
    public static void main(String[] args) {
        new PancakeSorting969().bridge();
    }

    private void bridge() {
        int[] A = {3, 2, 4, 1};
        new Solution().pancakeSort(A).forEach(System.out::println);
    }

    class Solution {
        public List<Integer> pancakeSort(int[] A) {
            List<Integer> ans = new ArrayList<>();
            int end = A.length;
            int[] copy = Arrays.copyOf(A, end);
            while (end > 0) {
                int maxIndex = getMaxIndex(copy, end);
                ans.add(maxIndex);
                copy = reverse(copy, maxIndex);
                ans.add(end);
                copy = reverse(copy, end);
                end--;
            }
            System.out.println(Arrays.toString(copy));
            return ans;
        }

        private int getMaxIndex(int[] A, int end) {
            int maxIndex = 0, max = A[0];
            for (int i = 0; i < end; i++) {
                if (A[i] > max) {
                    max = A[i];
                    maxIndex = i;
                }
            }
            return maxIndex + 1;
        }

        private int[] reverse(int[] A, int to) {
            int[] afterReverse = Arrays.copyOf(A, A.length);
            for (int i = 0; i < to; i++) {
                afterReverse[i] = A[to - 1 - i];
            }
            return afterReverse;
        }
    }
}
