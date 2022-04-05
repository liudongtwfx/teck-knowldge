package algorithme.stack;

import java.util.Stack;

public class ScoreOfParentheses856 {
    public static void main(String[] args) {
        new ScoreOfParentheses856().bridge();
    }

    private void bridge() {
        String s = "(()(()))";
        System.out.println(new Solution().scoreOfParentheses(s));
    }

    class Solution {
        public int scoreOfParentheses(String s) {
            int count = 0;
            Stack<Integer> stack = new Stack<>();
            char[] chars = s.toCharArray();
            int current = 1;
            for (char aChar : chars) {
                if (aChar == '(') {
                    if (stack.isEmpty()) {
                        current = 1;
                    }
                    continue;
                }
                stack.push(current);
            }
            return count;
        }
    }
}
