package algorithme.graph;

import java.util.*;

public class CloneGraph133 {
    public static void main(String[] args) {

    }

    class Solution {
        Set<Node> computed = new HashSet<>();
        private Node[] nodes = new Node[101];
        private Map<Integer, Set<Integer>> map = new HashMap<>();

        public Node cloneGraph(Node node) {
            if (node == null) return null;
            nodes[node.val] = new Node(node.val);
            dfs(node);
            map.forEach((k, v) -> {
                System.out.println(k);
                if (nodes[k] == null) nodes[k] = new Node(k);
                for (Integer child : v) {
                    System.out.println("--" + child);
                    if (nodes[child] == null) {
                        nodes[child] = new Node(child);
                    }
                    nodes[k].neighbors.add(nodes[child]);
                }
            });
            return nodes[node.val];
        }

        private void dfs(Node node) {
            if (computed.contains(node) || node.neighbors == null) {
                return;
            }
            computed.add(node);
            for (Node neighbor : node.neighbors) {
                putToMap(node.val, neighbor.val);
                putToMap(neighbor.val, node.val);
                dfs(neighbor);
            }
        }

        private void putToMap(Integer key, Integer value) {
            Set<Integer> values = map.getOrDefault(key, new LinkedHashSet<>());
            values.add(value);
            map.putIfAbsent(key, values);
        }
    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
