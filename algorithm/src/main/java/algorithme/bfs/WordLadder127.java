package algorithme.bfs;

import java.util.*;

public class WordLadder127 {
    public static void main(String[] args) {
        new WordLadder127().bridge();
    }

    private void bridge() {
        String startWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println(new Solution().ladderLength(startWord, endWord, wordList));
    }

    class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            Set<String> wordSet = new HashSet<>(wordList);
            Map<String, Integer> map = new HashMap<>();
            Queue<String> queue = new LinkedList<>();
            queue.add(beginWord);
            map.put(beginWord, 1);
            while (!queue.isEmpty()) {
                String head = queue.poll();
                if (head.equals(endWord)) {
                    return map.get(head);
                }
                List<String> strings = getNextLadder(wordSet, head);
                if (strings.isEmpty()) {
                    return 0;
                }
                for (String word : strings) {
                    if (!map.containsKey(word)) {
                        queue.offer(word);
                        map.put(word, map.get(head) + 1);
                    }
                }
            }
            return 0;
        }

        private List<String> getNextLadder(Set<String> wordList, String head) {
            List<String> result = new ArrayList<>();
            for (int i = 0; i < head.length(); i++) {
                char[] chars = head.toCharArray();
                for (int j = 0; j < 26; j++) {
                    chars[i] = (char) ('a' + j);
                    String newStr = new String(chars);
                    if (!head.equals(newStr) && wordList.contains(newStr)) {
                        result.add(newStr);
                    }
                }
            }
            return result;
        }
    }
}
