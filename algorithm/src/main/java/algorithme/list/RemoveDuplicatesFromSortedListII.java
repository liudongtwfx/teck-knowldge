package algorithme.list;

public class RemoveDuplicatesFromSortedListII {
    public static void main(String[] args) {

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
        public ListNode deleteDuplicates(ListNode head) {
            ListNode newNode = new ListNode(0);
            newNode.next = head;
            ListNode prev = newNode;
            ListNode next = head;
            while (head != null && head.next != null) {
                next = head.next;
                if (next.val != head.val) {
                    head = head.next;
                    prev = prev.next;
                    continue;
                }
                while (next != null && next.val == head.val) {
                    next = next.next;
                }
                prev.next = next;
                prev = prev.next;
                head = next;
            }
            return newNode.next;
        }
    }
}
