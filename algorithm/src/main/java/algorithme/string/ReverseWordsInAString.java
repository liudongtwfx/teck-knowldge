package algorithme.string;

public class ReverseWordsInAString {
    public static void main(String[] args) {
        new ReverseWordsInAString().bridge();
    }

    private void bridge() {
        String s = "  hello world  ";
        System.out.println(new Solution().reverseWords(s));
    }


    class Solution {
        public String reverseWords(String s) {
            String trimmed = s.trim();
            StringBuilder sb = new StringBuilder();
            StringBuilder reserver = new StringBuilder();
            char[] chars = trimmed.toCharArray();
            for (int i = trimmed.length() - 1; i >= 0; i--) {
                char c = chars[i];
                if (c == ' ' && i > 0 && chars[i + 1] == ' ') {
                    continue;
                }
                if (c == ' ') {
                    sb.append(reserve(reserver));
                    sb.append(" ");
                    reserver = new StringBuilder();
                    continue;
                }
                reserver.append(c);
            }
            sb.append(reserve(reserver));
            return sb.toString();
        }

        private String reserve(StringBuilder s) {
            return s.reverse().toString();
        }
    }
}
