package algorithme.heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaximumNumberOfWeeksForWhichYouCanWork1953 {
    public static void main(String[] args) {

    }


    class Solution {
        public long numberOfWeeks(int[] milestones) {
            Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
            for (int milestone : milestones) {
                queue.add(milestone);
            }
            long ans = 0;
            while (!queue.isEmpty()) {
                int a = queue.poll();
                ans++;
                if (!queue.isEmpty()) {
                    queue.poll();
                }
            }
            return ans;
        }
    }
}
