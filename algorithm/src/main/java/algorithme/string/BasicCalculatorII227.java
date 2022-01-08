package algorithme.string;

import java.util.Deque;
import java.util.LinkedList;

public class BasicCalculatorII227 {
    public static void main(String[] args) {
        new BasicCalculatorII227().bridge();
    }

    private void bridge() {
        String s = "3+2*2";
        System.out.println(new Solution().calculate(s));
    }

    class Solution {
        public int calculate(String s) {
            char[] chars = s.toCharArray();
            Deque<Integer> nums = new LinkedList<>();
            Deque<Character> ops = new LinkedList<>();
            int current = 0;
            for (char c : chars) {
                if (c == ' ') {
                    continue;
                }
                if (c >= '0' && c <= '9') {
                    current *= 10;
                    current += (c - '0');
                    continue;
                }
                boolean currentComputed = process(ops, nums, current);
                ops.offerLast(c);
                if (!currentComputed) {
                    nums.offerLast(current);
                }
                current = 0;
            }
            if (!process(ops, nums, current)) {
                nums.offerLast(current);
            }
            ops.offerFirst('+');
            int ans = 0;
            while (!ops.isEmpty()) {
                Character lastOp = ops.pollLast();
                Integer lastNum = nums.pollLast();
                if (lastOp == '+') {
                    ans += lastNum;
                } else {
                    ans -= lastNum;
                }
            }
            return ans;
        }

        private boolean process(Deque<Character> ops, Deque<Integer> nums, int current) {
            if (!ops.isEmpty() && !nums.isEmpty()) {
                Character last = ops.getLast();
                if (last == '*') {
                    nums.offerLast(nums.pollLast() * current);
                    ops.pollLast();
                    return true;
                }
                if (last == '/') {
                    nums.offerLast(nums.pollLast() / current);
                    ops.pollLast();
                    return true;
                }
            }
            return false;
        }
    }
}
