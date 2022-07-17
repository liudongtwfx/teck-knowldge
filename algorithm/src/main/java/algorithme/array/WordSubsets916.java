package algorithme.array;

import java.util.ArrayList;
import java.util.List;

public class WordSubsets916 {
    public static void main(String[] args) {

    }

    class Solution {
        public List<String> wordSubsets(String[] words1, String[] words2) {
            int[] maxCharSize = new int[26];
            for (String s : words2) {
                int[] maxLenOf = getCharCount(s);
                for (int i = 0; i < 26; i++) {
                    maxCharSize[i] = Math.max(maxCharSize[i], maxLenOf[i]);
                }
            }

            List<String> ans = new ArrayList<>();
            for (String w : words1) {
                int[] charCount = getCharCount(w);
                if (isMatch(maxCharSize, charCount)) {
                    ans.add(w);
                }
            }
            return ans;
        }

        private boolean isMatch(int[] maxCharSize, int[] charCount) {
            for (int i = 0; i < 26; i++) {
                if (maxCharSize[i] > charCount[i]) {
                    return false;
                }
            }
            return true;
        }

        private int[] getCharCount(String s) {
            int[] ans = new int[26];
            for (char c : s.toCharArray()) {
                ans[c - 'a']++;
            }
            return ans;
        }
    }
}
