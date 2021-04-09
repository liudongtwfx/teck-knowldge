package algorithme.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PopulatingNextRightPointersInEachNodeII {
    public static void main(String[] args) {

    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    class Solution {
        public Node connect(Node root) {
            List<Node> nodeList = new ArrayList<>();
            nodeList.add(root);
            while (!nodeList.isEmpty()) {
                List<Node> newLine = new ArrayList<>();
                for (int i = 0; i < nodeList.size(); i++) {
                    if (i < nodeList.size() - 1) {
                        nodeList.get(i).next = nodeList.get(i + 1);
                    }
                    if (nodeList.get(i).left != null) {
                        newLine.add(nodeList.get(i).left);
                    }
                    if (nodeList.get(i).right != null) {
                        newLine.add(nodeList.get(i).right);
                    }
                }
                nodeList = newLine;
            }
            return root;
        }
    }
}
