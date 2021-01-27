package algorithme.tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class BinarySearchTreeIterator173 {
    public static void main(String[] args) {

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


    /**
     * stack in-order traversal
     */
    class BSTIterator {
        private Deque<TreeNode> treeNodes;

        public BSTIterator(TreeNode root) {
            treeNodes = new ArrayDeque<>();
            while (root != null) {
                treeNodes.offerLast(root);
                root = root.left;
            }
        }

        public int next() {
            TreeNode last = treeNodes.getLast();
            treeNodes.pollLast();
            if (last.right != null) {
                treeNodes.offerLast(last.right);
                TreeNode left = last.right.left;
                while (left != null) {
                    treeNodes.offerLast(left);
                    left = left.left;
                }
            }
            return last.val;
        }

        public boolean hasNext() {
            return !treeNodes.isEmpty();
        }
    }
}
