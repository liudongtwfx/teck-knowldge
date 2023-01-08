package algorithme.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaximumBagsWithFullCapacityOfRocks2279 {
    public static void main(String[] args) {
        Queue<Integer> maxQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int a = 1;
        Math.floor(a / 2);
    }

    class Solution {
        public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
            int[][] sorted = new int[capacity.length][2];
            for (int i = 0; i < capacity.length; i++) {
                sorted[i][0] = capacity[i];
                sorted[i][1] = rocks[i];
            }
            Arrays.sort(sorted, (o1, o2) -> o1[0] - o1[1] - o2[0] + o2[1]);
            int ans = 0;
            for (int[] s : sorted) {
                if (s[0] - s[1] <= additionalRocks) {
                    ans++;
                    additionalRocks -= (s[0] - s[1]);
                }
            }
            return ans;
        }
    }
}
