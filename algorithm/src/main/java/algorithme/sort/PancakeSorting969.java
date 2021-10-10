package algorithme.sort;

import java.util.ArrayList;
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
        int[] A = {2, 1, 3};
        new Solution().pancakeSort(A).forEach(System.out::println);
    }

    class Solution {
        public List<Integer> pancakeSort(int[] arr) {
            List<Integer> nums = new ArrayList<>();
            dfs(arr, arr.length - 1, nums);
            return nums;
        }

        private void dfs(int[] arr, int end, List<Integer> nums) {
            if (end < 0) return;
            int maxIndex = 0;
            for (int i = 0; i <= end; i++) {
                if (arr[i] > arr[maxIndex]) maxIndex = i;
            }
            nums.add(maxIndex + 1);
            for (int i = 0; i * 2 <= maxIndex; i++) {
                int tmp = arr[i];
                arr[i] = arr[maxIndex - i];
                arr[maxIndex - i] = tmp;
            }
            int next = arr[0];
            nums.add(next);
            for (int i = 0; i * 2 <= end; i++) {
                int tmp = arr[i];
                arr[i] = arr[end - i];
                arr[end - i] = tmp;
            }
            dfs(arr, end - 1, nums);
        }
    }
}
