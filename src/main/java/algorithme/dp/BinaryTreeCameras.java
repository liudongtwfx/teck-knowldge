package algorithme.dp;

/**
 * 968. Binary Tree Cameras
 * https://leetcode.com/problems/binary-tree-cameras/
 */
public class BinaryTreeCameras {
    public static void main(String[] args) {
        new BinaryTreeCameras().bridge();
    }

    private void bridge() {
        TreeNode one = new TreeNode(0);
        TreeNode two = new TreeNode(0);
        TreeNode three = new TreeNode(0);
        TreeNode four = new TreeNode(0);
        TreeNode five = new TreeNode(0);
        one.left = two;
        two.left = three;
        three.left = four;
        four.right = five;
        System.out.println(new Solution().minCameraCover(one));
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

    class Solution {
        /**
         * HAVE FAITH IN DFS!
         * Three states-
         * 0 - Not covered by camera but children are covered (i.e. camera at grand children)
         * 1 - Covered by camera but no camera (i.e. camera at children)
         * 2 - Covered by camera and camera at this node
         **/
        int count = 0;

        public int minCameraCover(TreeNode root) {

            int result = dfs(root);
            // root isnt covered case 0
            if (result == 0) {
                count++;
            }
            return count;
        }

        private int dfs(TreeNode root) {
            if (root == null) return 1;

            int left = dfs(root.left);
            int right = dfs(root.right);

            // Case 0: camera at grand children level. so we need camera here
            if (left == 0 || right == 0) {
                count++;
                return 2;
            } else if (left == 1 && right == 1) {
                // case 1: camera at children level so we dont need camera here but might need in the next step
                return 0;
            } else {
                // case 2: camera current level so dont need camera now or next level
                return 1;
            }
        }
    }
}
