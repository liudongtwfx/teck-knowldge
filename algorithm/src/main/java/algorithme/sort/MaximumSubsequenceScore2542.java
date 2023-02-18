package algorithme.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaximumSubsequenceScore2542 {
    public static void main(String[] args) {

    }

    class Solution {
        public long maxScore(int[] nums1, int[] nums2, int k) {
            int length = nums1.length;
            int[][] scores = new int[length][2];
            for (int i = 0; i < length; i++) {
                scores[i][0] = nums1[i];
                scores[i][1] = nums2[i];
            }
            Arrays.sort(scores, Comparator.comparing(a -> a[1]));
            Queue<Integer> maxValue = new PriorityQueue<>();
            long total = 0;
            for (int i = length - 1; i >= length - k; i--) {
                maxValue.offer(scores[i][0]);
                total += scores[i][0];
            }
            long max = scores[length - k][1] * total;
            for (int i = length - k - 1; i >= 0; i--) {
                int min = scores[i][1];
                if (!maxValue.isEmpty()) {
                    int v = maxValue.peek();
                    if (v < scores[i][0]) {
                        maxValue.poll();
                        total += (scores[i][0] - v);
                        max = Math.max(max, min * total);
                        maxValue.offer(scores[i][0]);
                    }
                }
            }
            return max;
        }
    }
}
