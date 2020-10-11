package algorithme.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 1377. Frog Position After T Seconds
 * https://leetcode.com/problems/frog-position-after-t-seconds/
 */
public class FrogPositionAfterTSeconds {
    public static void main(String[] args) {
        new FrogPositionAfterTSeconds().bridge();
    }

    private void bridge() {
        int n = 7;
        int[][] edges = {{1, 2}, {1, 3}, {1, 7}, {2, 4}, {2, 6}, {3, 5}};
        int t = 2;
        int target = 4;
        System.out.println(new Solution().frogPosition(n, edges, t, target));
    }

    /**
     * bfs build
     */
    class Solution {
        private TreeNode[] nodesCache;

        public double frogPosition(int n, int[][] edges, int t, int target) {
            nodesCache = new TreeNode[n + 1];
            buildTree(edges, target);
            List<TreeNode> parents = new ArrayList<>();
            parents.add(nodesCache[target]);
            while (true) {
                TreeNode node = parents.get(parents.size() - 1);
                if (node.parent == null) {
                    break;
                }
                parents.add(node.parent);
            }
            if (parents.size() > t + 1) {
                return 0.0;
            }
            if (t > parents.size() - 1 && nodesCache[target].children.size() != 0) {
                return 0.0;
            }
            double ans = 1;
            for (TreeNode node : parents) {
                if (node.children.size() > 0) {
                    ans *= 1.0 / node.children.size();
                }
            }
            return ans;
        }

        private void buildTree(int[][] edges, int target) {
            TreeNode root = new TreeNode(1);
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);
            nodesCache[1] = root;
            while (!queue.isEmpty()) {
                TreeNode currentParent = queue.poll();
                for (int[] edge : edges) {
                    if (edge[0] == currentParent.value && nodesCache[edge[1]] == null) {
                        queue.add(putChild(currentParent, edge[1]));
                    }
                    if (edge[1] == currentParent.value && nodesCache[edge[0]] == null) {
                        queue.add(putChild(currentParent, edge[0]));
                    }
                }
                if (currentParent.value == target) {
                    return;
                }
            }
        }

        private TreeNode putChild(TreeNode currentParent, int childValue) {
            TreeNode node = new TreeNode(childValue);
            nodesCache[childValue] = node;
            currentParent.children.add(node);
            node.parent = currentParent;
            return node;
        }

        class TreeNode {
            int value;
            TreeNode parent;
            List<TreeNode> children;

            TreeNode(int value) {
                this.value = value;
                this.children = new ArrayList<>();
            }
        }
    }

    class DfsSolution {

    }
}
