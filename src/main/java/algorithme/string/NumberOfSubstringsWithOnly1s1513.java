package algorithme.string;

public class NumberOfSubstringsWithOnly1s1513 {
    public static void main(String[] args) {
        new NumberOfSubstringsWithOnly1s1513().bridge();
    }

    private void bridge() {
        String s = "0110111";
        System.out.println(new Solution().numSub(s));
    }

    class Solution {
        private static final int MODEL = 1000000000 + 7;

        public int numSub(String s) {
            long ans = 0;
            int start = 0, oneFrom = -1, oneTo = -1;
            while (start < s.length()) {
                if (s.charAt(start) == '1') {
                    oneTo = start;
                    if (oneFrom == -1) {
                        oneFrom = start;
                    }
                    start++;
                    continue;
                }
                if (s.charAt(start) == '0' && oneFrom != -1) {
                    ans = (ans + (oneTo - oneFrom + 1) * (oneTo - oneFrom + 2) / 2) % MODEL;
                    oneTo = -1;
                    oneFrom = -1;
                }
                start++;
            }
            if (s.charAt(s.length() - 1) == '1') {
                ans = (ans + (oneTo - oneFrom + 1) * (oneTo - oneFrom + 2) / 2) % MODEL;
            }
            return (int) ans;
        }
    }
}
