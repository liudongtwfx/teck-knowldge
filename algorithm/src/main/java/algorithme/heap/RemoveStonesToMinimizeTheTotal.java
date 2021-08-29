package algorithme.heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class RemoveStonesToMinimizeTheTotal {
    public static void main(String[] args) {
        new RemoveStonesToMinimizeTheTotal().bridge();
    }

    private void bridge() {
        int[] piles = {5, 4, 9};
        System.out.println(new Solution().minStoneSum(piles, 2));
    }

    class Solution {
        public int minStoneSum(int[] piles, int k) {
            Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
            int sum = 0;
            for (int pile : piles) {
                sum += pile;
                queue.add(pile);

            }
            int i = 1;
            while (i++ <= k && !queue.isEmpty()) {
                Integer poll = queue.poll();
                int removed = Math.floorDiv(poll, 2);
                queue.add(poll - removed);
                sum -= removed;

            }
            return sum;
        }
    }
}
