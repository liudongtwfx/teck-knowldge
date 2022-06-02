package algorithme.tree;

public class DeepestLeavesSum1302 {
    public static void main(String[] args) {
        new DeepestLeavesSum1302().bridge();
    }


    private void bridge() {
        String input = "[1,2,3,4,5,null,6,7,null,null,null,null,8]";
        TreeNode codec = new Codec().deserialize(input);
        System.out.println(new Solution().deepestLeavesSum(codec));
    }

    class Solution {
        private int maxDepth;
        private int value;

        public int deepestLeavesSum(TreeNode root) {
            dfsDepth(root, 0);
            dfs(root, 0);
            // System.out.println(maxDepth);
            return value;
        }

        private void dfsDepth(TreeNode node, int depth) {
            System.out.println(depth);
            if (node == null) return;
            if (node.left == null && node.right == null) {
                maxDepth = Math.max(maxDepth, depth);
                return;
            }
            dfsDepth(node.left, depth + 1);
            dfsDepth(node.right, depth + 1);
        }

        private void dfs(TreeNode node, int depth) {
            if (node == null) return;
            if (depth == maxDepth) {
                value += node.val;
            }
            dfs(node.left, depth + 1);
            dfs(node.right, depth + 1);
        }
    }
}
