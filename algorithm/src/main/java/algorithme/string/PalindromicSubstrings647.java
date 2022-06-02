package algorithme.string;

public class PalindromicSubstrings647 {
    public static void main(String[] args) {

    }

    class Solution {
        public int countSubstrings(String s) {
            char[] chars = s.toCharArray();
            int len = chars.length;
            int count = 0;
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < i; j++) {
                    if (chars[i] != chars[j]) {
                        continue;
                    }
                    if (isPalindrom(chars, i, j)) {
                        count++;
                    }
                }
            }
            return count;
        }

        private boolean isPalindrom(char[] chars, int start, int end) {
            while (start < end) {
                if (chars[start] != chars[end]) {
                    return false;
                }
                start++;
                end--;
            }
            return true;
        }
    }
}
