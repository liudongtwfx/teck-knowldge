package algorithme.array;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/distant-barcodes/
 * <p>
 * <p>
 * In a warehouse, there is a row of barcodes, where the i-th barcode is barcodes[i].
 * <p>
 * Rearrange the barcodes so that no two adjacent barcodes are equal.  You may return any answer, and it is guaranteed an answer exists.
 * <p>
 */
public class DistantBarcodes1054 {
    public static void main(String[] args) {
        new DistantBarcodes1054().bridge();
    }

    private void bridge() {
        int[] baracodes = {1, 1, 1, 2, 2, 2};
        System.out.println(Arrays.toString(new Solution().rearrangeBarcodes(baracodes)));
    }

    class Solution {
        public int[] rearrangeBarcodes(int[] barcodes) {
            Map<Integer, Integer> numCountMap = new HashMap<>();
            for (int barcode : barcodes) {
                numCountMap.merge(barcode, 1, Integer::sum);
            }
            Queue<NumCount> numCountQueue = new PriorityQueue<>();
            numCountMap.forEach((k, v) -> numCountQueue.offer(new NumCount(v, k)));
            int[] ans = new int[barcodes.length];
            for (int i = 0; i < barcodes.length; i++) {
                NumCount peek = numCountQueue.poll();
                if (peek == null) {
                    return ans;
                }
                if (i > 0 && peek.num == ans[i - 1]) {
                    NumCount secondLargest = numCountQueue.poll();
                    if (secondLargest == null) {
                        return ans;
                    }
                    secondLargest.count--;
                    if (secondLargest.count > 0) {
                        numCountQueue.offer(secondLargest);
                    }
                    ans[i] = secondLargest.num;
                    numCountQueue.offer(peek);
                    continue;
                }
                peek.count--;
                if (peek.count > 0) {
                    numCountQueue.offer(peek);
                }
                ans[i] = peek.num;
            }
            return ans;
        }

        class NumCount implements Comparable<NumCount> {
            private int count;
            private final int num;

            public NumCount(int count, int num) {
                this.count = count;
                this.num = num;
            }

            @Override
            public int compareTo(NumCount o) {
                return o.count - this.count;
            }
        }
    }
}
