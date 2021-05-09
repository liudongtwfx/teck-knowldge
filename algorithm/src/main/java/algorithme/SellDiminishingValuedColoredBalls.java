package algorithme;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class SellDiminishingValuedColoredBalls {
    public static void main(String[] args) {
        new SellDiminishingValuedColoredBalls().bridge();
    }

    private void bridge() {
        int[] inventory = {497978859, 167261111, 483575207, 591815159};
        int orders = 836556809;
        int i = new Solution().maxProfit(inventory, orders);
        System.out.println(i);
    }

    static class Solution {
        public int maxProfit(int[] stock, int orders) {
            final int mod = 1_000_000_007;
            long profit = 0;
            Queue<Integer> maxHeap = new PriorityQueue<>((a, b) -> (b - a));
            Arrays.stream(stock).forEach(maxHeap::add);
            long prevMaxCount = 0L;
            while (orders > 0) {
                long top = maxHeap.isEmpty() ? 0 : maxHeap.poll();
                long maxCount = 1 + prevMaxCount;
                while (!maxHeap.isEmpty() && maxHeap.peek() == top) {
                    maxHeap.poll();
                    maxCount++;
                }
                long second = maxHeap.isEmpty() ? 0L : maxHeap.peek();
                long diff = top - second;
                if (diff * maxCount >= orders) {
                    long toTake = orders / maxCount;
                    long left = orders - toTake * maxCount;
                    profit += ((((top + top - (toTake - 1)) * toTake) / 2) * maxCount) % mod;
                    profit %= mod;
                    profit += left * (top - toTake) % mod;
                    profit %= mod;
                    break;
                } else {
                    profit += ((((top + top - (diff - 1)) * diff) / 2) * maxCount) % mod;
                    profit %= mod;
                    prevMaxCount = maxCount;
                    orders -= diff * maxCount;
                }
            }
            return (int) profit;
        }
    }
}
