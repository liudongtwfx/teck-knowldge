package algorithme.stack;

import java.util.Stack;

public class RemoveDuplicateLetters {

    public static void main(String[] args) {
        new RemoveDuplicateLetters().bridge();
    }

    private void bridge() {
        String s = "cbacdcbc";
        System.out.println(new Solution().removeDuplicateLetters(s));
    }

    class Solution {
        public String removeDuplicateLetters(String s) {

            Stack<Character> stack = new Stack<Character>();

            // appearance count
            int[] count = new int[26];
            // used or not;
            boolean[] added = new boolean[26];

            // count appearances
            for (char ch : s.toCharArray())
                count[ch - 'a']++;

            // go through each char
            for (char ch : s.toCharArray()) {

                count[ch - 'a']--;

                if (added[ch - 'a'])
                    continue;

                // poping out the char which is bigger and still has some left in behind
                while (!stack.isEmpty() && stack.peek() > ch
                        && count[stack.peek() - 'a'] > 0)
                    added[stack.pop() - 'a'] = false;

                // add current one
                stack.push(ch);
                added[ch - 'a'] = true;
            }

            // move from stack to string
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }

            return sb.reverse().toString();

        }
    }

    class Solution1 {
        //记录每个字母的最后一个出现的位置
        //若两个字母出现降序且第一个字母有出现在第二个字母之后，将第一个字母删掉
        public String removeDuplicateLetters(String s) {
            int[] lastIndex = new int[26];
            boolean[] used = new boolean[26];
            for (int i = 0; i < s.length(); i++) {
                lastIndex[s.charAt(i) - 'a'] = i;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                //不能有重复
                if (used[s.charAt(i) - 'a']) continue;
                char cur = s.charAt(i);
                //字符串最后一个字母比现在字母大，且字符串最后一个字母的最后位置比当前字母要靠后，删掉字符串最后一个字母
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) > cur && lastIndex[sb.charAt(sb.length() - 1) - 'a'] > i) {
                    used[sb.charAt(sb.length() - 1) - 'a'] = false;
                    sb.deleteCharAt(sb.length() - 1);
                }
                sb.append(s.charAt(i));
                used[s.charAt(i) - 'a'] = true;
            }
            return sb.toString();
        }
    }
}
