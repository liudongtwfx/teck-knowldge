package algorithme.tree;

import java.util.ArrayList;
import java.util.List;

public class EvenOddTree1609 {
    public static void main(String[] args) {
        new EvenOddTree1609().bridge();
    }

    private void bridge() {
        String code = "[1,12,8,5,9,null,null,18,16]";
        Codec codec = new Codec();
        TreeNode root = codec.deserialize(code);
        System.out.println(new Solution().isEvenOddTree(root));
    }

    class Solution {
        public boolean isEvenOddTree(TreeNode root) {
            List<TreeNode> nodeList = new ArrayList<>();
            nodeList.add(root);
            int currentRow = 0;
            while (!nodeList.isEmpty()) {
                List<TreeNode> nextRow = new ArrayList<>();
                Integer last = null;
                for (TreeNode treeNode : nodeList) {
                    if (currentRow % 2 + treeNode.val % 2 != 1) {
                        return false;
                    }
                    if (last != null) {
                        if (currentRow % 2 == 0 && last >= treeNode.val) {
                            return false;
                        }

                        if (currentRow % 2 == 1 && last <= treeNode.val) {
                            return false;
                        }
                    }
                    last = treeNode.val;
                    if (treeNode.left != null) {
                        nextRow.add(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        nextRow.add(treeNode.right);
                    }
                }
                nodeList = nextRow;
                currentRow++;
            }
            return true;
        }
    }
}
