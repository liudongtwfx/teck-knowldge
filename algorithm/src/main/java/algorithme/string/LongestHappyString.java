package algorithme.string;

/**
 * 1405. Longest Happy String
 * https://leetcode.com/problems/longest-happy-string/
 */
public class LongestHappyString {
    public static void main(String[] args) {
        new LongestHappyString().bridge();
    }

    private void bridge() {
        int a = 1, b = 1, c = 7;
        System.out.println(new Solution().longestDiverseString(a, b, c));
    }

    class Solution {
        public String longestDiverseString(int a, int b, int c) {
            return getMax(a, b, c, "");
        }

        private String getMax(int a, int b, int c, String current) {
            if (a < 0 || b < 0 || c < 0) {
                return current.substring(0, current.length() - 1);
            }
            if (a > 0 && a >= b && a >= c) {
                if (!current.endsWith("aa")) {
                    return getMax(a - 1, b, c, current + "a");
                } else if (b >= c) {
                    return getMax(a, b - 1, c, current + "b");
                }
                return getMax(a, b, c - 1, current + "c");
            }
            if (b > 0 && b >= a && b >= c) {
                if (!current.endsWith("bb")) {
                    return getMax(a, b - 1, c, current + "b");
                } else if (a >= c) {
                    return getMax(a - 1, b, c, current + "a");
                }
                return getMax(a, b, c - 1, current + "c");
            }
            if (c > 0) {
                if (!current.endsWith("cc")) {
                    return getMax(a, b, c - 1, current + "c");
                } else if (a >= b) {
                    return getMax(a - 1, b, c, current + "a");
                }
                return getMax(a, b - 1, c, current + "b");
            }
            return current;
        }
    }
}
