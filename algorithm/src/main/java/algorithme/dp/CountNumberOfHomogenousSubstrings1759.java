package algorithme.dp;

public class CountNumberOfHomogenousSubstrings1759 {
    public static void main(String[] args) {
        new CountNumberOfHomogenousSubstrings1759().bridge();
    }

    private void bridge() {
        String s = "zzzzz";
        System.out.println(new Solution().countHomogenous(s));
    }

    class Solution {
        public int countHomogenous(String s) {
            long ans = 0;
            char[] chars = s.toCharArray();
            int i = 0;
            while (i < chars.length) {
                int j = i;
                while (j < chars.length - 1 && chars[j] == chars[j + 1]) {
                    j++;
                }
                long tmp = (long) (j - i + 1) * (j - i + 2) / 2;
                ans = (ans + tmp) % (1000000007);
                i = j + 1;
            }
            return (int) ans;
        }
    }
}
