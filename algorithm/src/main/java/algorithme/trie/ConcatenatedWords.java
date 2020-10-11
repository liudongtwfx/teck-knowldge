package algorithme.trie;

import java.util.*;

/**
 * 472. Concatenated Words
 * https://leetcode.com/problems/concatenated-words/
 */
public class ConcatenatedWords {
    public static void main(String[] args) {
        String[] words = {"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"};
        new ConcatenatedWords().bridge(words);
    }

    private void bridge(String[] words) {
        List<String> allConcatenatedWordsInADict = new Solution().findAllConcatenatedWordsInADict(words);
        allConcatenatedWordsInADict.forEach(System.out::println);
    }

    class Solution {
        public List<String> findAllConcatenatedWordsInADict(String[] words) {
            Arrays.sort(words, Comparator.comparingInt(String::length));
            Set<String> wordsSet = new HashSet<>();
            List<String> ans = new ArrayList<>();
            for (String word : words) {
                if (dfs(word, wordsSet)) {
                    ans.add(word);
                }
                wordsSet.add(word);
            }
            return ans;
        }

        private boolean dfs(String word, Set<String> wordsSet) {
            if (word == null || word.length() == 0) {
                return true;
            }
            for (int i = 0; i < word.length(); i++) {
                if (wordsSet.contains(word.substring(0, i + 1))) {
                    String suffix = word.substring(i + 1);
                    if (wordsSet.contains(suffix) || dfs(suffix, wordsSet)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
