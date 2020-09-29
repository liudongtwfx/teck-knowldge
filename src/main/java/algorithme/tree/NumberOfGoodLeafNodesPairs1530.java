package algorithme.tree;

import java.util.*;

public class NumberOfGoodLeafNodesPairs1530 {
    public static void main(String[] args) {
        new NumberOfGoodLeafNodesPairs1530().bridge();
    }

    private void bridge() {
        String data = "[63,62,99,15,85,37,50,85,82,65,36,94,23,73,66,97,48,21,53,47,55,97,56,46,14,16,46,28,93,14,76,60,7,19,39,2,58,12,95,96,69,27,81,39,61,10,26,61,52,5,4,62,66,25,8,17,1,69,18,33,50,33,77,92,31,10,50,41,64,26,1,13,27,35,42,98,90,36,14,100,28,19,22,3,1,86,30,51,92,14,55,6,20,18,35,15,8,46,51,42,63,21,31,43,5,91,33,56,19,3,11,48,4,95,51,7,37,47,11,37,19,46,94,44,93,48,89,30,1,41,82,100,1,61,66,47,98,47,27,64,49,11,13,13,12,63,23,25,60,76,97,25,37,82,62,74,64,44,80,38,21,90,56,34,22,65,30,97,89,34,20,31,25,15,80,99,71,13,16,6,91,87,74,81,2,87,20,36,92,79,64,25,85,20,1,37,15,90,15,73,100,90,55,41,83,29,60,8,18,63,40,63,68,70,88,72,21,58,27,66,39,95,50,5,38,7,92,90,16,35,35,86,43,43,23,72,100,60,77,83,55,54,97,20,27,74,12,33,80,57,47,50,97,17,58,9,23,42,56,79,19,79,3,46,66,87,69,64,84,27,77,66,74,16,72,78,64,65,25,76,23,29,35,23,25,6,92,81,32,31,23,84,37,43,66,3,51,94,14,39,2,56,54,97,57,19,9,76,55,49,71,7,98,6,9,48,76,3,33,51,32,15,17,69,62,58,12,50,97,24,54,8,63,39,26,97,79,20,20,43,77,9,71,30,67,61,44,66,16,61,78,16,90,26,5,36,7,30,19,76,71,72,78,90,12,91,62,50,91,52,26,91,74,3,16,46,94,98,72,10,28,84,23,69,56,14,95,28,66,58,47,37,13,87,57,95,44,62,61,24,92,55,21,78,31,59,36,59,93,63,11,40,54,88,69,88,31,90,30,64,31,47,68,34,91,43,38,55,41,18,28,75,66,96,44,83,91,1,21,96,84,52,18,13,92,17,69,40,15,6,31,16,98,17,43,95,63,70,28,91,39,100,27,89,43,46,64,74,8,42,30,37,71,25,78,29,61,62,78,99,49,31,65,31,6,11,75,25,87,33,66,89,59,60,76,60,38,7,20,38,36,43,79,59,52,12,17,18,73,61,85,59,93,20,90,93,89,54,29,82,15,67,86,29,62,50,33,60,96,99,65,86,28,15,41,45,76,12,43,35,99,80,98,74,85,3,57,25,84,69,70,9,83,68,78,92,4,13,29,45,87,47,91,10,46,66,76,12,88,48,40,51,47,94,22,11,66,35,42,89,4,20,93,76,66,4,17,79,83,89,53,83,49,40,46,87,45,60,51,70,29,1,18,55,21,94,47,2,92,85,56,51,3,89,48,55,37,39,87,97,31,17,45,98,94,5,46,36,51,29,26,59,28,33,61,48,80,55,52,8,48,32,4,70,11,43,28,75,3,21,1,72,48,38,21,53,93,50,48,3,11,5,85,8,26,85,21,18,28,8,14,50,2,99,82,51,6,52,35,98,13,78,69,90,35,64,36,18,95,74,96,21,5,33,53,18,98,37,82,86,74,84,50,85,34,46,30,29,99,19,3,96,51,37,16,87,9,53,4,25,60,95,94,97,35,31,7,44,91,90,5,26,79,70,55,8,66,96,21,81,26,12,32,30,97,84,15,12,60,22,41,32,80,78,29,42,15,69,18,73,58,70,91,35,89,42,58,47,30,12,42,99,20,22]";
        TreeNode deserialize = new Codec().deserialize(data);
        System.out.println(new Solution().countPairs(deserialize, 4));
    }

    class Solution {
        public int countPairs(TreeNode root, int distance) {
            Map<TreeNode, List<TreeNode>> nodeToParentMap = new HashMap<>();
            List<TreeNode> leafNodes = new ArrayList<>();
            dfs(root, leafNodes, nodeToParentMap, new ArrayList<>());
            int count = 0;
            for (int i = 0; i < leafNodes.size(); i++) {
                for (int j = i + 1; j < leafNodes.size(); j++) {
                    // TreeNode treeNode = lowestCommonAncestor(root, leafNodes.get(i), leafNodes.get(j));
                    int d = getDistance(nodeToParentMap, leafNodes.get(i), leafNodes.get(j));
                    if (d <= distance) {
                        count++;
                    }
                }
            }
            return count;
        }

        private int getDistance(Map<TreeNode, List<TreeNode>> nodeToParentMap, TreeNode p, TreeNode q) {
            List<TreeNode> parentListP = nodeToParentMap.get(p);
            List<TreeNode> parentListQ = nodeToParentMap.get(q);
            int i = parentListP.size() - 1, j = parentListQ.size() - 1;
            while (parentListP.get(i) == parentListQ.get(j)) {
                i--;
                j--;
            }
            return i + j + 2;
        }

        private void dfs(TreeNode node, List<TreeNode> leafNodes, Map<TreeNode, List<TreeNode>> nodeToParentMap, List<TreeNode> parentList) {
            if (node == null) {
                return;
            }
            parentList.add(0, node);
            if (node.left == null && node.right == null) {
                leafNodes.add(node);
                nodeToParentMap.put(node, new ArrayList<>(parentList));
                nodeToParentMap.put(node, Collections.unmodifiableList(parentList));
            }
            dfs(node.left, leafNodes, nodeToParentMap, parentList);
            dfs(node.right, leafNodes, nodeToParentMap, parentList);
            parentList.remove(0);
        }
    }
}
