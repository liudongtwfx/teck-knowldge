package algorithme.dfs;

public class SplittingAStringIntoDescendingConsecutiveValues1849 {
    public static void main(String[] args) {
        new SplittingAStringIntoDescendingConsecutiveValues1849().bridge();
    }

    private void bridge() {
        Solution solution = new Solution();
        String s = "9080701";
        System.out.println(solution.splitString(s));
    }

    class Solution {
        public boolean splitString(String s) {
            int num = 0;
            for (int i = 0; i < s.length() - 1; i++) {
                num = num * 10 + Integer.parseInt(s.substring(i, i + 1));
                if (dfs(s, i + 1, num)) {
                    return true;
                }
            }
            return false;
        }

        private boolean dfs(String s, int start, int last) {
            if (start >= s.length()) {
                return true;
            }
            int value = 0;
            for (int i = start; i < s.length(); i++) {
                value = value * 10 + Integer.parseInt(s.substring(i, i + 1));
                if (value == last - 1 && dfs(s, i + 1, value)) {
                    return true;
                }
            }
            return false;
        }
    }
}
