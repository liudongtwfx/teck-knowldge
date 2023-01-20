package algorithme.greedy;

import algorithme.InputUtils;

import java.util.Arrays;

public class MinimumNumberOfArrowsToBurstBalloons452 {
    public static void main(String[] args) {
        new MinimumNumberOfArrowsToBurstBalloons452().bridge();
    }

    private void bridge() {
        String input = "[[-2147483646,-2147483645],[2147483646,2147483647]]";
        // jifkow-sidpem-kEsmy8
        System.out.println(new Solution().findMinArrowShots(InputUtils.convertToArray(input)));
    }

    class Solution {
        public int findMinArrowShots(int[][] points) {
            Arrays.sort(points, (o1, o2) -> {
                if (o1[1] == o2[1]) return 0;
                return o1[1] < o2[1] ? -1 : 1;
            });
            int ans = 0;
            long max = Long.MIN_VALUE;
            for (int[] point : points) {
                if (point[0] > max) {
                    ans++;
                    max = point[1];
                }
            }
            return ans;
        }
    }
}
