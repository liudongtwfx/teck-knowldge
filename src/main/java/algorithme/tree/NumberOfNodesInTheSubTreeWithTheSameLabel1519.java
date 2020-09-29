package algorithme.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberOfNodesInTheSubTreeWithTheSameLabel1519 {
    public static void main(String[] args) {
        new NumberOfNodesInTheSubTreeWithTheSameLabel1519().bridge();
    }

    private void bridge() {
        int n = 4;
        int[][] edges = {{0, 2}, {0, 3}, {1, 2}};
        String labels = "aeed";
        System.out.println(Arrays.toString(new Solution().countSubTrees(n, edges, labels)));
    }

    class Solution {
        public int[] countSubTrees(int n, int[][] edges, String labels) {
            TreeNode root = buildTree(n, edges, labels);
            int[] ans = new int[n];
            dfs(root, ans);
            return ans;
        }

        private int[] dfs(TreeNode node, int[] ans) {
            if (node == null) return new int[26];
            int[] newCharCount = new int[26];
            for (TreeNode child : node.children) {
                int[] count = dfs(child, ans);
                for (int i = 0; i < count.length; i++) {
                    newCharCount[i] += count[i];
                }
            }
            newCharCount[node.label - 'a']++;
            // System.out.println("currentIndex:" + node.index + "count: " + Arrays.toString(newCharCount));
            ans[node.index] = newCharCount[node.label - 'a'];
            return newCharCount;
        }

        private TreeNode buildTree(int n, int[][] edges, String labels) {
            TreeNode[] nodes = new TreeNode[n];
            boolean[] visited = new boolean[n];
            for (int i = 0; i < n; i++) {
                nodes[i] = new TreeNode(i, new ArrayList<>(), labels.charAt(i));
            }
            visited[0] = true;
            for (int[] edge : edges) {
                int from = visited[edge[0]] ? edge[0] : edge[1];
                int to = from == edge[1] ? edge[0] : edge[1];
                nodes[from].children.add(nodes[to]);
                visited[from] = true;
                visited[to] = true;
            }
            return nodes[0];
        }

        class TreeNode {
            private final int index;
            private final List<TreeNode> children;
            private final char label;

            public TreeNode(int index, List<TreeNode> children, char label) {
                this.index = index;
                this.children = children;
                this.label = label;
            }
        }
    }
}
