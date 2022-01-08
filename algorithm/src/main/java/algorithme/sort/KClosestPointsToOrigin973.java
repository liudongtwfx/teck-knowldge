package algorithme.sort;

import java.util.Arrays;

public class KClosestPointsToOrigin973 {


    class Solution {
        public int[][] kClosest(int[][] points, int k) {
            int n = points.length;
            Arrays.sort(points, (o1, o2) -> {
                int left = o1[0] * o1[0] + o1[1] * o1[1];
                int right = o2[0] * o2[0] + o2[1] * o2[1];
                return left - right;
            });
            int[][] ans = new int[k][2];
            for (int i = 0; i < k; i++) {
                ans[i][0] = points[i][0];
                ans[i][1] = points[i][1];
            }
            return ans;
        }
    }
}
