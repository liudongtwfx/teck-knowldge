package algorithme.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConcatenatedWords472 {
    public static void main(String[] args) {

    }

    class Solution {
        public List<String> findAllConcatenatedWordsInADict(String[] words) {
            Arrays.sort(words, (o1, o2) -> {
                if (o1.length() != o2.length()) {
                    return o1.length() - o2.length();
                }
                return o1.compareTo(o2);
            });
            List<String> res = new ArrayList<>();
            Trie root = new Trie();
            for (String word : words) {
                if (isShort(word, root)) {
                    res.add(word);
                }
                addToTrie(word, root);
            }
            return res;
        }

        private void addToTrie(String word, Trie root) {
            Trie node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (node.children[c - 'a'] == null) {
                    Trie trie = new Trie();
                    trie.c = c;
                    node.children[c - 'a'] = trie;
                }
                node.children[c - 'a'].leafNode |= i == word.length() - 1;
                node = node.children[c - 'a'];
            }
        }

        private boolean isShort(String word, Trie root) {
            Trie node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                node = node.children[c - 'a'];
                if (node == null) {
                    return false;
                }
                if (node.leafNode) {
                    if (i == word.length() - 1 || isShort(word.substring(i + 1), root)) {
                        return true;
                    }
                }
            }
            return false;
        }

        class Trie {
            private char c;
            private boolean leafNode;
            private final Trie[] children = new Trie[26];
        }
    }
}
