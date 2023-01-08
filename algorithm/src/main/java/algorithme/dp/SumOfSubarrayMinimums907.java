package algorithme.dp;

import java.util.Deque;
import java.util.LinkedList;

public class SumOfSubarrayMinimums907 {
    public static void main(String[] args) {
        new SumOfSubarrayMinimums907().bridge();
    }

    private void bridge() {
        int[] arr = {71, 55, 82, 55};
        System.out.println(new Solution().sumSubarrayMins(arr));
    }

    class Solution {
        public int sumSubarrayMins(int[] arr) {
            int n = arr.length;
            int[] nextSmaller = new int[n];
            Deque<Integer> queue = new LinkedList<>();
            long ans = 0;
            for (int i = n - 1; i >= 0; i--) {
                nextSmaller[i] = nextSmaller(queue, arr, i, true);
            }
            queue.clear();
            for (int i = 0; i < n; i++) {
                int leftSmaller = nextSmaller(queue, arr, i, false);
                int len = nextSmaller[i] - leftSmaller + 1;
                int leftLen = i - leftSmaller;
                int rightLen = nextSmaller[i] - i;
                int count = (len * (len + 1) - (leftLen) * (leftLen + 1) - rightLen * (rightLen + 1)) / 2;
                ans = (ans + (long) count * arr[i]) % 1000000007;
                // System.out.printf("num:%s,count:%s\n", arr[i], count);
            }
            return (int) ans;
        }

        private int nextSmaller(Deque<Integer> queue, int[] arr, int i, boolean right) {
            int value = arr[i];
            if (queue.isEmpty()) {
                queue.offerLast(i);
                return right ? arr.length - 1 : 0;
            }
            while (!queue.isEmpty() && conditionCheck(queue, arr, value, right)) {
                queue.pollLast();
            }
            int lastValue;
            if (right) {
                lastValue = (queue.isEmpty() ? arr.length - 1 : queue.peekLast() - 1);
            } else {
                lastValue = (queue.isEmpty() ? 0 : queue.peekLast() + 1);
            }
            queue.offerLast(i);
            return lastValue;
        }

        private boolean conditionCheck(Deque<Integer> queue, int[] arr, int value, boolean right) {
            if (right) {
                return arr[queue.peekLast()] >= value;
            }
            return arr[queue.peekLast()] > value;
        }
    }
}
