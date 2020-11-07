package algorithme.tree;

/**
 * 1373: Maximum Sum BST in Binary Tree
 * https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/
 */
public class MaximumSumBSTinBinaryTree {
    public static void main(String[] args) {
        new MaximumSumBSTinBinaryTree().bridge();
    }

    private void bridge() {
        TreeNode root = new TreeNode(4);
        TreeNode one = new TreeNode(1);
        TreeNode three = new TreeNode(5);

        TreeNode treeNode = new TreeNode(8);
        TreeNode treeNode1 = new TreeNode(6);
        TreeNode treeNode2 = new TreeNode(3);

        root.right = one;
        one.left = three;
        three.right = treeNode;

        System.out.println(new Solution().maxSumBST(root));
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        int maxValue = 0;

        public int maxSumBST(TreeNode root) {
            postOrderSearch(root, null, 0);
            return maxValue;
        }

        private Integer postOrderSearch(TreeNode node, Integer type, int val) {
            if (node == null) return 0;
            Integer allLeft = 0, allRight = 0;
            if (node.left != null) {
                allLeft = postOrderSearch(node.left, 1, node.val);
            }
            if (node.right != null) {
                allRight = postOrderSearch(node.right, 2, node.val);
            }
            Integer temp = null;
            if (allLeft != null && allRight != null) {
                temp = allLeft + allRight + node.val;
                maxValue = Math.max(maxValue, temp);
            }
            if (type != null && type == 1) {
                if (node.val >= val) {
                    return null;
                }
            }

            if (type != null && type == 2) {
                if (node.val <= val) {
                    return null;
                }
            }
            return temp;
        }
    }
}
