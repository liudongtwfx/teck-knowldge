package algorithme.trie;

import java.util.*;

/**
 * 692. Top K Frequent Words
 * https://leetcode.com/problems/top-k-frequent-words/
 */
public class TopKFrequentWords {
    public static void main(String[] args) {
        String[] words = {"a", "aa", "aaa"};
        new Solution().topKFrequent(words, 1).forEach(System.out::println);
    }

    private static class Solution {

        public List<String> topKFrequent(String[] words, int k) {
            if (words.length == 0) {
                return Collections.emptyList();
            }
            Map<String, Integer> strCountMap = new HashMap<>();
            for (String word : words) {
                strCountMap.merge(word, 1, Integer::sum);
            }
            TrieNode[] nodes = new TrieNode[words.length];
            for (String word : words) {
                int count = strCountMap.get(word);
                TrieNode node = nodes[count];
                nodes[count] = appendWordTrie(node, word);
            }
            List<String> res = new ArrayList<>();
            for (int i = words.length - 1; i >= 0; i--) {
                res.addAll(getStringsFromTrieRoot(nodes[i]));
                if (res.size() >= k) {
                    return res.subList(0, k);
                }
            }
            return new ArrayList<>();
        }

        private List<String> getStringsFromTrieRoot(TrieNode node) {
            if (node == null || node.children == null) {
                return Collections.emptyList();
            }
            List<String> words = new ArrayList<>();
            for (TrieNode child : node.children) {
                dfs(child, words);
            }
            return words;
        }

        private void dfs(TrieNode node, List<String> words) {
            if (node == null) {
                return;
            }
            if (node.value != null) {
                words.add(node.value);
            }
            if (node.children == null) {
                return;
            }
            for (TrieNode child : node.children) {
                dfs(child, words);
            }
        }

        private TrieNode appendWordTrie(TrieNode node, String word) {
            if (node == null) {
                node = new TrieNode();
            }
            appendCharToTrie(node, word, 0);
            return node;
        }

        private void appendCharToTrie(TrieNode node, String word, int i) {
            if (i > word.length()) {
                return;
            }
            if (i == word.length()) {
                node.value = word;
                return;
            }
            char c = word.charAt(i);
            if (node.children == null) {
                node.children = new TrieNode[26];
            }
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            appendCharToTrie(node.children[c - 'a'], word, i + 1);
        }
    }

    private static class TrieNode {
        String value;
        TrieNode[] children;
    }
}
