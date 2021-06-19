package algorithme;

import java.util.HashSet;
import java.util.Set;

public class MinimumSpeedToArriveOnTime1870 {
    public static void main(String[] args) {
        new MinimumSpeedToArriveOnTime1870().bridge();
    }

    private void bridge() {
        int[] dist = {1, 1, 100000};
        double hour = 2.01;
        int i = new Solution().minSpeedOnTime(dist, hour);
        System.out.println(i);
    }

    class Solution {
        private final Set<Integer> canReached = new HashSet<>();

        public int minSpeedOnTime(int[] dist, double hour) {
            binary(dist, hour, 1, 1000000000);
            int ans = Integer.MAX_VALUE;
            for (Integer integer : canReached) {
                ans = Math.min(ans, integer);
            }
            return ans < Integer.MAX_VALUE ? ans : -1;
        }

        private int binary(int[] dist, double hours, int min, int max) {
            if (max - min <= 2) {
                for (int i = min; i <= max; i++) {
                    if (canReach(dist, hours, i)) {
                        canReached.add(i);
                    }
                }
                return 0;
            }
            int half = (min + max) / 2;
            if (canReached.contains(half)) {
                return 0;
            }
            if (canReach(dist, hours, half)) {
                canReached.add(half);
                return binary(dist, hours, min, half - 1);
            }
            return binary(dist, hours, half + 1, max);
        }

        private boolean canReach(int[] dist, double hour, int currentSpeed) {
            double total = 0;
            for (int i = 0; i < dist.length - 1; i++) {
                int v = dist[i];
                if (v % currentSpeed == 0) {
                    total += ((double) v / currentSpeed);
                } else {
                    total += ((double) (v / currentSpeed) + 1);
                }
                if (total > hour) {
                    return false;
                }
            }
            System.out.println("currentSpeed:" + currentSpeed + ":" + (total <= hour));
            return total + (double) dist[dist.length - 1] / currentSpeed <= hour;
        }
    }
}
