package algorithme.dp;

import java.util.PriorityQueue;
import java.util.Queue;

public class MaximumDeletionsOnAString2430 {
    public static void main(String[] args) {
        new MaximumDeletionsOnAString2430().bridge();
    }

    private void bridge() {
        String s = "aaabaab";
        System.out.println(new Solution().deleteString(s));
    }

    class Solution {
        public int deleteString(String s) {
            int len = s.length();
            Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
            char[] chars = s.toCharArray();
            for (int i = len - 1; i >= 0; i--) {
                int max = 1;
                for (int[] q : queue) {
                    int strLen = q[0] - i;
                    if (isEquals(chars, i, q[0], strLen)) {
                        max = q[1] + 1;
                        break;
                    }
                }
                queue.offer(new int[]{i, max});
                if (i == 0) {
                    return max;
                }
            }
            return 0;
        }

        private boolean isEquals(char[] chars, int start, int end, int strLen) {
            for (int i = 0; i < strLen; i++) {
                if (start + i >= chars.length || end + i >= chars.length
                        || chars[start + i] != chars[end + i]) {
                    return false;
                }
            }
            return true;
        }
    }
}
