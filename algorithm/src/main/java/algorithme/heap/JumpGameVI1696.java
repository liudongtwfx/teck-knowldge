package algorithme.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class JumpGameVI1696 {
    public static void main(String[] args) {
        new JumpGameVI1696().bridge();
    }

    private void bridge() {
        int[] nums = {1, -1, -2, 4, -7, 3};
        int k = 2;
        System.out.println(new Solution().maxResult(nums, k));
    }

    class Solution {
        public int maxResult(int[] nums, int k) {
            int[] res = new int[nums.length];
            res[0] = nums[0];
            Queue<Point> points = new PriorityQueue<>((a, b) -> b.res - a.res);
            points.offer(new Point(res[0], 0));
            for (int i = 1; i < nums.length; i++) {
                // res[i] = Integer.MIN_VALUE;
                int left = i - k;
                while (true) {
                    Point p = points.peek();
                    if (p.index < left) {
                        points.poll();
                    }
                    break;
                }
                res[i] = nums[i] + points.peek().res;
                points.offer(new Point(res[i], i));
                //System.out.println(res[i]);
            }
            // System.out.println(new Gson().toJson(res));
            return res[res.length - 1];
        }

        class Point {
            private final int res;
            private final int index;

            Point(int res, int index) {
                this.res = res;
                this.index = index;
            }
        }
    }
}
