package algorithme.tree;

import java.util.HashMap;
import java.util.Map;

public class PathSumIII {
    public static void main(String[] args) {
        new PathSumIII().bridge();
    }

    private void bridge() {
        Codec codec = new Codec();
        TreeNode deserialize = codec.deserialize("[5,4,8,11,null,13,4,7,2,null,null,5,1]");
        System.out.println(new Solution().pathSum(deserialize, 22));
    }

    /**
     * @author liudong
     */
    static class Solution {
        private Map<Integer, Integer> sumCountMap = new HashMap<>();
        private int count = 0;
        private int targetSum;

        public int pathSum(TreeNode root, int targetSum) {
            this.targetSum = targetSum;
            // sumCountMap.put(0, 1);
            inOrder(root, 0);
            return count;
        }

        private void inOrder(TreeNode node, int currentTotal) {
            if (node == null) {
                return;
            }
            int prev = currentTotal + node.val - targetSum;
            count += sumCountMap.getOrDefault(prev, 0);
            sumCountMap.merge(currentTotal + node.val, 1, Integer::sum);
            System.out.println(count + "   " + node.val);
            if (currentTotal + node.val == targetSum) {
                count++;
            }
            inOrder(node.left, currentTotal + node.val);
            inOrder(node.right, currentTotal + node.val);
            sumCountMap.merge(currentTotal + node.val, 1, (a, b) -> a - b);
            if (sumCountMap.get(currentTotal + node.val) == 0) {
                sumCountMap.remove(currentTotal + node.val);
            }
        }
    }
}
