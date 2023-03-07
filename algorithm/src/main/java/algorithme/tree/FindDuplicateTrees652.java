package algorithme.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDuplicateTrees652 {
    public static void main(String[] args) {
        new FindDuplicateTrees652().bridge();
    }

    private void bridge() {
        String input = "[0,0,0,0,null,null,0,0,0,0,0]";
        TreeNode treeNode = Codec.deserialize(input);
        List<TreeNode> treeNodes = new Solution().findDuplicateSubtrees(treeNode);
        for (TreeNode node : treeNodes) {
            System.out.println(node.val);
        }
    }

    class Solution {
        Map<String, Integer> nodesMap = new HashMap<>();
        List<TreeNode> treeNodes = new ArrayList<>();

        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            dfs(root);
            return treeNodes;
        }

        private String dfs(TreeNode node) {
            if (node == null) return "null";
            String left = dfs(node.left);
            String right = dfs(node.right);
            String currentKey = node.val + "_" + left + "_" + right;
            int originValue = nodesMap.getOrDefault(currentKey, 0);
            if (originValue == 1) {
                treeNodes.add(node);
            }
            nodesMap.put(currentKey, originValue + 1);
            return currentKey;
        }
    }
}
