package algorithme.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Codec {

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        List<String> str = new ArrayList<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        while (!nodeQueue.isEmpty()) {
            TreeNode head = nodeQueue.poll();
            if (head != null) {
                str.add(String.valueOf(head.val));
                nodeQueue.offer(head.left);
                nodeQueue.offer(head.right);
            } else {
                str.add("null");
            }
        }
        return "[" + String.join(",", str) + "]";
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        String[] numStrs = data.substring(1, data.length() - 1).split(",");
        if (numStrs[0].equals("null")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(numStrs[0]));
        Queue<TreeNode> buildQueue = new LinkedList<>();
        buildQueue.add(root);
        int i = 1;
        while (i < numStrs.length) {
            TreeNode head = buildQueue.poll();
            if (head == null) continue;
            if (numStrs[i].equals("null")) {
                head.left = null;
            } else {
                TreeNode left = new TreeNode(Integer.parseInt(numStrs[i]));
                head.left = left;
                buildQueue.offer(left);
            }
            i++;
            if (i >= numStrs.length) {
                break;
            }
            if (numStrs[i].equals("null")) {
                head.right = null;
            } else {
                TreeNode right = new TreeNode(Integer.parseInt(numStrs[i]));
                head.right = right;
                buildQueue.offer(right);
            }
            i++;
        }
        return root;
    }
}
