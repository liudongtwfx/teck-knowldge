package algorithme.dp;

import algorithme.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class HouseRobber337 {
    public static void main(String[] args) {
        int n = 16;
        System.out.println(n & (n - 1));
    }

    class Solution {
        private Map<TreeNode, Integer> maxMap = new HashMap<>();

        public int rob(TreeNode root) {
            return dfs(root);
        }

        private int dfs(TreeNode node) {
            if (node == null) {
                return 0;
            }
            if (node.left != null) {
                dfs(node.left);
            }
            if (node.right != null) {
                dfs(node.right);
            }
            int include = 0;
            if (node.left != null) {
                include += maxMap.getOrDefault(node.left.left, 0);
                include += maxMap.getOrDefault(node.left.right, 0);
            }
            if (node.right != null) {
                include += maxMap.getOrDefault(node.right.left, 0);
                include += maxMap.getOrDefault(node.right.right, 0);
            }
            int res = Math.max(include + node.val, maxMap.getOrDefault(node.left, 0) + maxMap.getOrDefault(node.right, 0));
            maxMap.put(node, res);
            return res;
        }
    }
}
