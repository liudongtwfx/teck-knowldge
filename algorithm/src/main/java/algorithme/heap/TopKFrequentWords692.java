package algorithme.heap;

import java.util.*;
import java.util.stream.Collectors;

public class TopKFrequentWords692 {
    public static void main(String[] args) {
        new TopKFrequentWords692().bridge();
    }

    private void bridge() {
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        System.out.println(new Solution().topKFrequent(words, 2));
    }

    class Solution {
        public List<String> topKFrequent(String[] words, int k) {
            Map<String, Integer> frequent = new HashMap<>();
            for (String word : words) {
                frequent.merge(word, 1, Integer::sum);
            }
            Queue<Node> queue = new PriorityQueue<>();
            for (Map.Entry<String, Integer> entry : frequent.entrySet()) {
                queue.offer(new Node(entry.getKey(), entry.getValue()));
                if (queue.size() > k) {
                    queue.poll();
                }
            }
            return queue.stream().sorted(Comparator.reverseOrder()).map(n -> n.word).collect(Collectors.toList());
        }

        class Node implements Comparable<Node> {
            String word;
            int count;

            public Node(String word, int count) {
                this.word = word;
                this.count = count;
            }

            @Override
            public int compareTo(Node o) {
                if (o.count != count) return this.count - o.count;
                return o.word.compareTo(this.word);
            }
        }
    }
}
