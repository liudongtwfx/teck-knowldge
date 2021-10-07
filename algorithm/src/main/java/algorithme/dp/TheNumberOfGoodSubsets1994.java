package algorithme.dp;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class TheNumberOfGoodSubsets1994 {
    public static void main(String[] args) {
        new TheNumberOfGoodSubsets1994().bridge();
    }

    private void bridge() {
        int[] nums = {18, 28, 2, 17, 29, 30, 15, 9, 12};
        System.out.println(new Solution().numberOfGoodSubsets(nums));
    }

    static class Solution {
        static int mod = (int) 1e9 + 7;
        static int[] map = new int[31];

        static {
            int[] prime = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
            for (int i = 2; i <= 30; ++i) {
                if (0 == i % 4 || 0 == i % 9 || 25 == i) continue;
                int mask = 0;
                for (int j = 0; j < 10; ++j) {
                    if (0 == i % prime[j]) mask |= 1 << j;
                }
                map[i] = mask;
            }
        }

        public int numberOfGoodSubsets(int[] nums) {
            int n = nums.length, one = 0;
            int[] dp = new int[1024], cnt = new int[31];
            dp[0] = 1;
            for (int i : nums) {
                if (1 == i) one++;
                else if (0 != map[i]) cnt[i]++;
            }
            for (int i = 0; i < 31; ++i) {
                if (0 == cnt[i]) continue;
                for (int j = 0; j < 1024; ++j) {
                    if (0 != (j & map[i])) continue;
                    dp[j | map[i]] = (int) ((dp[j | map[i]] + dp[j] * (long) cnt[i]) % mod);
                }
            }
            long res = 0;
            for (int i : dp) res = (res + i) % mod;
            res--;
            if (0 != one) res = res * pow(one) % mod;
            return (int) res;
        }

        private long pow(int n) {
            long res = 1, m = 2;
            while (0 != n) {
                if (1 == (n & 1)) res = (res * m) % mod;
                m = m * m % mod;
                n >>= 1;
            }
            return res;
        }
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    class Solution1 {
        Queue<ListNode> nodeQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));

        public ListNode mergeKLists(ListNode[] lists) {
            ListNode head = null, p = head;
            for (int i = 0; i < lists.length; i++) {
                nodeQueue.offer(lists[i]);
            }
            ListNode node = null;
            while ((node = getNext()) != null) {
                // System.out.println(node.val);
                if (head == null) {
                    head = new ListNode(node.val);
                }
                if (p == null) {
                    p = head;
                }
                p.next = new ListNode(node.val);
                p = p.next;
            }
            return head == null ? null : head.next;
        }

        private ListNode getNext() {
            if (nodeQueue.isEmpty()) return null;
            ListNode node = nodeQueue.poll();
            if (node.next != null) {
                nodeQueue.offer(node.next);
            }
            return node;
        }
    }
}