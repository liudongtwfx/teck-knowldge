package algorithme.dp;


import java.util.HashSet;
import java.util.Set;

/**
 * 1339. Maximum Product of Splitted Binary Tree
 * https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/
 *
 * @author liudong
 */
public class MaximumProductOfSplittedBinaryTree {
    public static void main(String[] args) {

    }

    class Solution {
        public int maxProduct(TreeNode root) {
            Set<Integer> nodeSumMap = new HashSet<>();
            long total = dfs(root, nodeSumMap);
            long ans = 0;
            for (Integer value : nodeSumMap) {
                if (value != 0 && value != total) {
                    ans = Math.max(ans, ((long) value * (total - value)));
                }
            }
            return (int) (ans % ((int) 1e9 + 7));
        }

        private int dfs(TreeNode node, Set<Integer> nodeSumMap) {
            if (node == null) {
                return 0;
            }
            int left = dfs(node.left, nodeSumMap);
            int right = dfs(node.right, nodeSumMap);
            int tmp = left + right + node.val;
            nodeSumMap.add(tmp);
            return tmp;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
