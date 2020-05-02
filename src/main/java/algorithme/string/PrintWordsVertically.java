package algorithme.string;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * 1324. Print Words Vertically
 * https://leetcode.com/problems/print-words-vertically/
 */
public class PrintWordsVertically {
    public static void main(String[] args) {

    }

    class Solution {
        public List<String> printVertically(String s) {
            String[] strs = s.split(" ");
            int max = 0;
            for (String str : strs) {
                max = Math.max(max, str.length());
            }
            List<StringBuilder> ans = new ArrayList<>();
            for (int i = 0; i < max; i++) {
                ans.add(new StringBuilder());
            }
            for (String str : strs) {
                char[] chars = str.toCharArray();
                for (int i = 0; i < max; i++) {
                    char c = i >= chars.length ? ' ' : chars[i];
                    ans.get(i).append(c);
                }
            }
            return ans.stream().map(this::rightTrim).collect(toList());
        }

        private String rightTrim(StringBuilder s) {
            String ans = s.toString();
            for (int i = ans.length() - 1; i >= 0; i--) {
                if (ans.charAt(i) != ' ') {
                    return ans.substring(0, i + 1);
                }
            }
            return ans;
        }
    }
}
