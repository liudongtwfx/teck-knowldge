package algorithme.stack;

import java.util.Stack;

public class RemoveDuplicateLetters {

    public static void main(String[] args) {
        new RemoveDuplicateLetters().bridge();
    }

    private void bridge() {
        String s = "abacb";
        System.out.println(new Solution().removeDuplicateLetters(s));
    }

    class Solution {
        public String removeDuplicateLetters(String s) {
            int length = s.length();
            int[] count = new int[length];
            int current = 0;
            for (int i = length - 1; i >= 0; i--) {
                current |= 1 << (s.charAt(i) - 'a');
                count[i] = current;
            }
            Stack<Character> characters = new Stack<>();
            boolean[] added = new boolean[26];
            for (int i = 0; i < length; i++) {
                char c = s.charAt(i);
                if (added[c - 'a']) {
                    continue;
                }
                while (!characters.isEmpty()) {
                    char pop = characters.peek();
                    if (pop < c) {
                        break;
                    }
                    int i1 = count[i] & (1 << (pop - 'a'));
                    if (i1 != 0) {
                        characters.pop();
                        added[pop - 'a'] = false;
                    } else {
                        break;
                    }
                }
                characters.add(c);
                added[c - 'a'] = true;
            }
            char[] ans = new char[characters.size()];
            for (int i = characters.size() - 1; i >= 0; i--) {
                ans[i] = characters.pop();
            }
            return new String(ans);
        }
    }
}
