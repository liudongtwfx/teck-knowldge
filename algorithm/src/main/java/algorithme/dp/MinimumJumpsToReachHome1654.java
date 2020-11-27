package algorithme.dp;

import java.util.Objects;

public class MinimumJumpsToReachHome1654 {
    public static void main(String[] args) {
        new MinimumJumpsToReachHome1654().bridge();
    }

    private void bridge() {
        int[] forbidden = {1998};
        int a = 1999;
        int b = 2000;
        int x = 2000;
        Solution solution1 = new Solution();
        System.out.println(solution1.minimumJumps(forbidden, a, b, x));
        //System.out.println(solution1.visitCount);
    }

    class Solution {
        public int minimumJumps(int[] forbidden, int a, int b, int x) {
            int[] nums = new int[10001];
            int head = 0, tail = 1;
            boolean[] forbiddentSet = new boolean[10001];
            for (int i : forbidden) {
                forbiddentSet[i] = true;
            }
            int[][] positionStepMap = new int[10001][2];
            positionStepMap[0][0] = 0;
            positionStepMap[0][1] = 1;
            int count = 0;
            while (head < tail) {
                int position = nums[head++];
                count++;
                //System.out.println(position);
                int[] stepAndDirection = positionStepMap[position];
                if (Objects.equals(position, x)) {
                    System.out.println(count);
                    return stepAndDirection[0];
                }

                int back = position - b;
                if (stepAndDirection[1] != 2 && back >= 0 && positionStepMap[back][0] == 0 && !forbiddentSet[back]) {
                    nums[tail++] = back;
                    positionStepMap[back][0] = stepAndDirection[0] + 1;
                    positionStepMap[back][1] = 2;
                }
                int forward = position + a;
                if (forward <= 6000 && positionStepMap[forward][0] == 0 && !forbiddentSet[forward]) {
                    nums[tail++] = forward;
                    positionStepMap[forward][0] = stepAndDirection[0] + 1;
                    positionStepMap[forward][1] = 1;
                }
            }
            return -1;
        }
    }

    class Solution1 extends Solution {
        boolean[] forbid;
        boolean[] visit;
        int res = Integer.MAX_VALUE;
        int visitCount = 0;

        @Override
        public int minimumJumps(int[] forbidden, int a, int b, int x) {
            forbid = new boolean[10000];
            visit = new boolean[10000];
            for (int f : forbidden)
                forbid[f] = true;
            if (forbid[0])
                return -1;
            helper(0, 0, a, b, x, false);
            return res == Integer.MAX_VALUE ? -1 : res;
        }

        public void helper(int curr, int count, int a, int b, int x, boolean back) {
            visitCount++;
            if (curr == x) {
                res = count;
                return;
            }
            if (!back)
                visit[curr] = true;
            if (count >= res)
                return;
            if (curr + a < visit.length && !forbid[curr + a] && !visit[curr + a])
                helper(curr + a, count + 1, a, b, x, false);
            if (!back && curr - b > 0 && !forbid[curr - b] && !visit[curr - b])
                helper(curr - b, count + 1, a, b, x, true);
        }
    }
}
