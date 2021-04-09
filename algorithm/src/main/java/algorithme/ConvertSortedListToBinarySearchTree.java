package algorithme;

public class ConvertSortedListToBinarySearchTree {
    public static void main(String[] args) {
        new ConvertSortedListToBinarySearchTree().bridge();
    }

    private void bridge() {
        int[] nums = {-10, -3, 0, 5, 9};
        ListNode head = new ListNode(nums[0]);
        ListNode node = head;
        for (int i = 1; i < nums.length; i++) {
            ListNode newNode = new ListNode(nums[i]);
            node.next = newNode;
            node = newNode;
        }
        TreeNode treeNode = new Solution().sortedListToBST(head);
        System.out.println(treeNode.val);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {
        public TreeNode sortedListToBST(ListNode head) {
            return innerListToBST(head);
        }

        private TreeNode innerListToBST(ListNode node) {
            if (node == null) {
                return null;
            }
            int count = 0;
            ListNode head = node;
            while (head != null) {
                ++count;
                head = head.next;
            }
            System.out.println("count:" + count);
            if (count == 1) {
                return new TreeNode(node.val);
            }
            int half = (count + 1) / 2;
            count = 1;
            ListNode newHead = new ListNode(node.val);
            ListNode newP = newHead;
            head = node;
            while (count < half - 1) {
                count++;
                head = head.next;
                ListNode listNode = new ListNode(head.val);
                newP.next = listNode;
                newP = listNode;
            }
            TreeNode treeNode = new TreeNode(head.next.val);
            treeNode.left = innerListToBST(newHead);
            treeNode.right = innerListToBST(head.next.next);
            return treeNode;
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
