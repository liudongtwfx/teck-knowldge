package algorithme.design;

public class DesignAddAndSearchWordsDataStructure211 {
    public static void main(String[] args) {

    }

    class WordDictionary {
        private Trie root;

        public WordDictionary() {
            this.root = new Trie(' ');
        }

        public void addWord(String word) {
            char[] chars = word.toCharArray();
            Trie node = root;
            for (int i = 0; i < chars.length; i++) {
                Trie next = node.children[chars[i] - 'a'];
                if (next == null) {
                    node.children[chars[i] - 'a'] = new Trie(chars[i]);
                }
                if (i == chars.length - 1) {
                    node.children[chars[i] - 'a'].end = true;
                }
                node = node.children[chars[i] - 'a'];
            }
        }

        public boolean search(String word) {
            char[] chars = word.toCharArray();
            return dfs(chars, 0, root);
        }

        private boolean dfs(char[] chars, int currentIndex, Trie currentNode) {
            if (currentIndex >= chars.length) {
                return currentNode.end;
            }
            char c = chars[currentIndex];
            if (c == '.') {
                for (int i = 0; i < 26; i++) {
                    if (dfs(chars, currentIndex + 1, currentNode.children[i])) {
                        return true;
                    }
                }
                return false;
            }
            return dfs(chars, currentIndex + 1, currentNode.children[c - 'a']);
        }

        class Trie {
            char c;
            boolean end = false;
            Trie[] children = new Trie[26];

            Trie(char c) {
                this.c = c;
            }
        }
    }
}
