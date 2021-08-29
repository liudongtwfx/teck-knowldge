package algorithme.list;

public class OddEvenLinkedList {
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
        public ListNode oddEvenList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode oddHead = head;
            ListNode evenHead = head.next;
            int index = 2;
            ListNode p = head.next.next, oddP = oddHead, evenP = evenHead;
            while (p != null) {
                System.out.println(index + ":" + p.val);
                if (index % 2 == 1) {
                    evenP.next = p;
                    evenP = evenP.next;
                } else {
                    oddP.next = p;
                    oddP = oddP.next;
                }
                p = p.next;
                index++;
            }
            oddP.next = evenHead;
            evenP.next = null;
            return oddHead;
        }
    }
}
