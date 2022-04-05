package algorithme.list;

public class SwappingNodesInALinkedList1721 {
    public static void main(String[] args) {

    }

    class Solution {
        public ListNode swapNodes(ListNode head, int k) {
            ListNode p = head;
            int length = 0;
            while (p != null) {
                length++;
                p = p.next;
            }
            k = Math.min(k, length - k);
            int nk = length - k;
            ListNode kNode = null;
            ListNode kLeftNode = null;
            ListNode nkNode = null;
            ListNode nkLeftNode = null;
            int i = 0;
            p = head;
            while (p != null) {
                i++;
                if (i == k - 1) {
                    kLeftNode = p;
                }
                if (i == k) {
                    kNode = p;
                }
                if (i == nk) {
                    nkLeftNode = p;
                }
                if (i == nk + 1) {
                    nkNode = p;
                }
                p = p.next;
            }
            if (k == nk) {
                swap(kLeftNode, kNode, nkLeftNode, nkNode);
                return head;
            }
            ListNode kRightNode = kNode.next;
            kNode.next = nkNode.next;
            nkNode.next = kRightNode;
            if (kLeftNode != null) {
                kLeftNode.next = nkNode;
            }
            if (nkLeftNode != null) {
                nkLeftNode.next = kNode;
            }
            return head;
        }

        private void swap(ListNode kLeftNode, ListNode kNode, ListNode nkLeftNode, ListNode nkNode) {
            kNode.next = nkNode;
            nkLeftNode.next = kNode;
            if (kLeftNode != null) {
                kLeftNode.next = nkLeftNode;
            }
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
