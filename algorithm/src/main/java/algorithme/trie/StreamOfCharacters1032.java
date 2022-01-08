package algorithme.trie;

import java.util.ArrayList;
import java.util.List;

public class StreamOfCharacters1032 {
    public static void main(String[] args) {
        new StreamOfCharacters1032().bridge();
    }

    private void bridge() {
        StreamChecker streamChecker = new StreamChecker(new String[]{"cd", "f", "kl"});
        for (int i = 0; i < 12; i++) {
            System.out.println(streamChecker.query((char) ('a' + i)));
        }
    }

    class StreamChecker {
        List<Character> characters = new ArrayList<>();
        private Trie root = new Trie('0');

        public StreamChecker(String[] words) {
            for (String word : words) {
                buildTrie(word);
            }
        }

        public boolean query(char letter) {
            characters.add(letter);
            Trie node = root;
            for (int i = characters.size() - 1; i >= 0; i--) {
                Trie child = node.children[characters.get(i) - 'a'];
                if (child == null) {
                    return false;
                }
                if (child.end) {
                    return true;
                }
                node = child;
            }
            return false;
        }

        private void buildTrie(String word) {
            char[] chars = word.toCharArray();
            Trie parent = root;
            for (int i = chars.length - 1; i >= 0; i--) {
                if (parent.children[chars[i] - 'a'] == null) {
                    parent.children[chars[i] - 'a'] = new Trie(chars[i]);
                }
                parent = parent.children[chars[i] - 'a'];
            }
            parent.end = true;
        }

        class Trie {
            private char c;
            private Trie[] children;
            private boolean end;

            Trie(char c) {
                this.c = c;
                this.children = new Trie[26];
            }
        }
    }
}
