package algorithme;

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

    class Solution {
        public int maxProfit(int[] inventory, int orders) {
            final int MOD = 1_000_000_007;
            Queue<Integer> inventoryQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
            for (int i : inventory) {
                inventoryQueue.offer(i);
            }
            long ans = 0;
            while (orders > 0) {
                Integer first = inventoryQueue.poll();
                if (first == null) {
                    return (int) ans;
                }
                Integer second = inventoryQueue.peek() != null ? inventoryQueue.peek() : 0;
                int count = Math.min(orders, first - second + 1);
                long a = (long) (first - count + first + 1) * count / 2;
                ans = (ans + a) % MOD;
                System.out.println(count);
                orders -= count;
                inventoryQueue.offer(first - count);
            }
            return (int) ans;
        }
    }
}
