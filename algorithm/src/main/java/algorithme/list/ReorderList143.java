package algorithme.list;

import java.util.Deque;
import java.util.LinkedList;

public class ReorderList143 {
    public static void main(String[] args) {
        new ReorderList143().bridge();
    }

    private void bridge() {
        ListNode three = new ListNode(3, new ListNode(4, null));
        ListNode two = new ListNode(2, three);
        ListNode one = new ListNode(1, two);
        new Solution().reorderList(one);
        ListNode node = one;
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    class Solution {
        public void reorderList(ListNode head) {
            Deque<ListNode> queue = new LinkedList<>();
            ListNode p = head;
            while (p != null) {
                queue.addLast(p);
                p = p.next;
            }
            ListNode left = head;
            ListNode right = queue.pollLast();
            while (left != right && left.next != right) {
                right.next = left.next;
                left.next = right;
                left = right.next;
                right = queue.pollLast();
            }
            right.next = null;
        }
    }
}
