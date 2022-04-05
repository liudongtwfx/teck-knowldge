package algorithme.tree;

import java.util.ArrayList;
import java.util.List;

public class MaximumWidthOfBinaryTree662 {
    public static void main(String[] args) {
        new MaximumWidthOfBinaryTree662().bridge();
    }

    private void bridge() {
        String test = "[1,1,1,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,null,1,1,null,1,null,1,null,1,null,1,null]";
        TreeNode deserialize = new Codec().deserialize(test);
        System.out.println(new Solution().widthOfBinaryTree(deserialize));
    }

    class Solution {
        public int widthOfBinaryTree(TreeNode root) {
            return bfs(root);
        }

        private int bfs(TreeNode root) {
            List<NodeInfo> levelNode = new ArrayList<>();
            levelNode.add(new NodeInfo(root, 1));
            int ans = 0;
            while (!levelNode.isEmpty()) {
                List<NodeInfo> info = new ArrayList<>();
                ans = Math.max(ans, levelNode.get(levelNode.size() - 1).no - levelNode.get(0).no + 1);
                for (NodeInfo nodeInfo : levelNode) {
                    //System.out.print(nodeInfo.no+" ");
                    if (nodeInfo.node.left != null) {
                        info.add(new NodeInfo(nodeInfo.node.left, nodeInfo.no * 2 - 1));
                    }
                    if (nodeInfo.node.right != null) {
                        info.add(new NodeInfo(nodeInfo.node.right, nodeInfo.no * 2));
                    }
                }
                //System.out.println();
                levelNode = info;
            }
            return ans;
        }

        class NodeInfo {
            TreeNode node;
            int no;

            public NodeInfo(TreeNode node, int no) {
                this.node = node;
                this.no = no;
            }
        }
    }
}
