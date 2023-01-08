package algorithme.stack;

import java.util.Deque;
import java.util.LinkedList;

public class BasicCalculator224 {
    public static void main(String[] args) {
        new BasicCalculator224().bridge();
    }

    private void bridge() {
        System.out.println(new Solution().calculate("2-4-(8+2-6+(8+4-(1)+8-10))"));
    }

    class Solution {
        public int calculate(String s) {
            char[] chars = s.toCharArray();
            int currentNum = 0;
            int value = 0;
            char lastOpt = '+';
            Deque<Boolean> optStack = new LinkedList<>();
            for (char c : chars) {
                if (c == ' ') continue;
                if (c >= '0' && c <= '9') {
                    currentNum *= 10;
                    currentNum += (c - '0');
                    continue;
                }
                if (c == '+' || c == '-') {
                    if (lastOpt == '+') {
                        value += currentNum;
                    } else {
                        value -= currentNum;
                    }
                    currentNum = 0;
                }
                if (c == '(') {
                    optStack.add(lastOpt == '-');
                } else if (c == ')') {
                    optStack.pollLast();
                } else if (!optStack.isEmpty() && optStack.peekLast()) {
                    lastOpt = (c == '+' ? '-' : '+');
                } else {
                    lastOpt = (c == '+' ? '+' : '-');
                }
            }
            if (lastOpt == '+') {
                value += currentNum;
            } else {
                value -= currentNum;
            }
            return value;
        }
    }
}
