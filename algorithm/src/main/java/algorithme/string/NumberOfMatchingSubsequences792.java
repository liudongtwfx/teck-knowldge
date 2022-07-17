package algorithme.string;

import java.util.*;

public class NumberOfMatchingSubsequences792 {
    public static void main(String[] args) {
        new NumberOfMatchingSubsequences792().bridge();
    }

    private void bridge() {
        String test = "abcde";
        String[] words = {"a", "bb", "acd", "ace"};
        System.out.println(new Solution().numMatchingSubseq(test, words));
    }

    class Solution {
        public int numMatchingSubseq(String s, String[] words) {
            TreeSet<Integer>[] charIndexMap = new TreeSet[26];
            for (int i = 0; i < s.length(); i++) {
                TreeSet<Integer> indexSet = charIndexMap[s.charAt(i) - 'a'];
                if (indexSet == null) {
                    TreeSet<Integer> newTreeSet = new TreeSet<>();
                    newTreeSet.add(i);
                    charIndexMap[s.charAt(i) - 'a'] = newTreeSet;
                } else {
                    indexSet.add(i);
                }
            }
            int count = 0;
            for (String word : words) {
                if (isSub(word, charIndexMap)) {
                    count++;
                }
            }
            return count;
        }

        private boolean isSub(String word, TreeSet<Integer>[] charIndexMap) {
            int fromIndex = 0;
            for (char c : word.toCharArray()) {
                if (charIndexMap[c - 'a'] == null) {
                    return false;
                }
                Integer nextIndex = charIndexMap[c - 'a'].ceiling(fromIndex);
                if (nextIndex == null) {
                    return false;
                }
                fromIndex = nextIndex + 1;
            }
            return true;
        }
    }
}
