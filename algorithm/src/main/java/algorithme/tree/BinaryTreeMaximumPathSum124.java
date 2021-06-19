package algorithme.tree;

public class BinaryTreeMaximumPathSum124 {
    public static void main(String[] args) {

    }

    class Solution {
        private int maxValue;

        public int maxPathSum(TreeNode root) {
            if (root == null) {
                return 0;
            }
            maxValue = Integer.MIN_VALUE;
            totalValue(root);
            return maxValue;
        }

        private int totalValue(TreeNode root) {
            if (root == null) return 0;
            int leftValue = totalValue(root.left);
            int rightValue = totalValue(root.right);

            int a = Math.max(leftValue, rightValue);
            maxValue = Math.max(maxValue, leftValue + rightValue + root.val);
            maxValue = Math.max(maxValue, Math.max(0, a) + root.val);
            return Math.max(0, a) + root.val;
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
