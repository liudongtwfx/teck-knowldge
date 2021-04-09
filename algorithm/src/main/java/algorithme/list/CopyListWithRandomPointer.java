package algorithme.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CopyListWithRandomPointer {
    public static void main(String[] args) {

    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    class Solution {
        public Node copyRandomList(Node head) {
            Node p = head;
            List<Node> newNodes = new ArrayList<>();
            Map<Node, Integer> originNodeIndexMap = new HashMap<>();
            while (p != null) {
                Node node = new Node(p.val);
                if (newNodes.size() > 0) {
                    newNodes.get(newNodes.size() - 1).next = node;
                }
                newNodes.add(node);
                originNodeIndexMap.put(p, originNodeIndexMap.size());
                p = p.next;
            }

            p = head;
            while (p != null) {
                if (p.random != null) {
                    int index = originNodeIndexMap.get(p.random);
                    newNodes.get(originNodeIndexMap.get(p)).random = newNodes.get(index);
                }
                p = p.next;
            }
            return newNodes.get(0);
        }
    }
}
