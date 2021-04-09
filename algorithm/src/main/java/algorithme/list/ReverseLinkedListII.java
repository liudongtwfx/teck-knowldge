package algorithme.list;

/**
 * Reverse a linked list from position m to n. Do it in one-pass.
 * <p>
 * Note: 1 ≤ m ≤ n ≤ length of list.
 */
public class ReverseLinkedListII {
    public static void main(String[] args) {
        new ReverseLinkedListII().bridge();
    }

    private void bridge() {
        ListNode one = new ListNode(3, new ListNode(5));
        ListNode second = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));

        Solution solution = new Solution();
        ListNode node = solution.reverseBetween(one, 1, 2);
        printElement(node);
        printElement(solution.reverseBetween(second, 2, 4));
    }

    private void printElement(ListNode node) {
        int max = 1;
        while (node != null && max++ < 100) {
            System.out.println(node.val);
            node = node.next;
        }

        System.out.println("-------------print end--------------");
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

    /**
     * 1-->2-->3-->4-->5-->6-->7
     * 1-->2-->5-->4-->3-->6-->7
     */
    class Solution {
        public ListNode reverseBetween(ListNode head, int m, int n) {
            if (m == n) return head;
            ListNode dummy = new ListNode();
            dummy.next = head;

            // init pointers, h is head of reversed area, it will be tail of the area after reversed
            ListNode prev = null, cur = dummy, nxt = dummy.next, h = null;
            for (int i = 1; i <= n; i++) {
                // a temp pointer to cur as pre before move cur
                ListNode pre = cur;
                // move cur to next
                cur = nxt;
                // move next to its next
                nxt = nxt.next;
                if (i == m) {
                    // found start of reverse area
                    prev = pre;
                    h = cur;
                } else if (i > m) {
                    // do reverse
                    cur.next = pre;
                }
            }
            // after reverse area, point prev to reversed head which is cur, point h to suffix which is nxt
            prev.next = cur;
            h.next = nxt;
            return dummy.next;
        }
    }
}
