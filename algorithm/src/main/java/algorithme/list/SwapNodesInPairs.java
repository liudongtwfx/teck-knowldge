package algorithme.list;

public class SwapNodesInPairs {
    public static void main(String[] args) {

    }

    class Solution {
        public ListNode swapPairs(ListNode head) {
            ListNode dummy = new ListNode();
            dummy.next = head;
            ListNode p = dummy;
            ListNode next = head;
            while (p != null) {
                p.next = next.next;
                next.next = next.next.next;
                p = p.next;
                next = next.next;
            }
            return dummy.next;
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
}
