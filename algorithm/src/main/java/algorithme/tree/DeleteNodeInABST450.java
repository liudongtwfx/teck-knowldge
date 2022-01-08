package algorithme.tree;

public class DeleteNodeInABST450 {
    public static void main(String[] args) {
    }

    class Solution {

        public TreeNode deleteNode(TreeNode root, int key) {
            inOrder(root, key, null);
            return root;
        }

        private void inOrder(TreeNode node, int key, TreeNode parent) {
            if (node == null) {
                return;
            }
            if (node.val > key) {
                inOrder(node.left, key, node);
                return;
            }
            if (node.val < key) {
                inOrder(node.right, key, node);
            }

        }
    }
}
