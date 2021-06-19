package algorithme.tree;

import java.util.Objects;

public class LongestUnivaluePath687 {
    public static void main(String[] args) {

    }

    class Solution {
        private int max;

        public int longestUnivaluePath(TreeNode root) {
            dfs(root, null);
            return max;
        }

        private int dfs(TreeNode root, Integer lastValue) {
            if (root == null) return 0;
            int leftSameCount = dfs(root.left, root.val);
            int rightSameCount = dfs(root.right, root.val);
            if (root.left != null && Objects.equals(root.left.val, root.val)) {
                leftSameCount++;
            }
            if (root.left != null && !Objects.equals(root.left.val, root.val)) {
                leftSameCount = 0;
            }
            if (root.right != null && Objects.equals(root.right.val, root.val)) {
                rightSameCount++;
            }
            if (root.right != null && !Objects.equals(root.right.val, root.val)) {
                rightSameCount = 0;
            }
            max = Math.max(max, leftSameCount + rightSameCount);
            return Math.max(leftSameCount, rightSameCount);
        }
    }
}
