package algorithme.stack;

public class MinimumNumberOfSwapsToMakeTheStringBalanced1963 {
    public static void main(String[] args) {
        new MinimumNumberOfSwapsToMakeTheStringBalanced1963().bridge();
    }

    private void bridge() {
        String s = "]]][[[";
        System.out.println(new Solution().minSwaps(s));
    }

    class Solution {
        public int minSwaps(String s) {
            int i = 0, j = s.length() - 1;
            char[] chars = s.toCharArray();
            int leftCount = 0, ans = 0;
            while (i < j) {
                if (chars[i] == '[') {
                    leftCount++;
                    i++;
                    continue;
                }
                if (leftCount > 0) {
                    leftCount--;
                    i++;
                    continue;
                }
                while (i <= j && chars[j] != '[') {
                    j--;
                }
                ans++;
                chars[j] = ']';
                chars[i] = '[';
                leftCount++;
                i++;
                j--;
            }
            return ans;
        }
    }
}
