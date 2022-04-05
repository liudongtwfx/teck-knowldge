package algorithme.greedy;

import java.util.Arrays;

public class TwoCityScheduling1029 {
    public static void main(String[] args) {

    }

    class Solution {
        public int twoCitySchedCost(int[][] costs) {
            Arrays.sort(costs, (o1, o2) -> o1[0] - o1[1] + o2[1] - o2[0]);
            int ans = 0;
            int len = costs.length;
            for (int i = 0; i < len; i++) {
                if (i * 2 < len) {
                    ans += costs[i][0];
                } else {
                    ans += costs[i][1];
                }
            }
            return ans;
        }
    }
}
