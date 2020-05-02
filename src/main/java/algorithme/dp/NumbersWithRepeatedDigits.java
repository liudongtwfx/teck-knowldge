package algorithme.dp;

/**
 * 1012. Numbers With Repeated Digits
 * https://leetcode.com/problems/numbers-with-repeated-digits/
 *
 * @author liudong
 */
public class NumbersWithRepeatedDigits {
    public static void main(String[] args) {
        new NumbersWithRepeatedDigits().bridge();
    }

    private void bridge() {
        int N = 200;
        System.out.println(new Solution().numDupDigitsAtMostN(N));
    }

    class Solution {
        public int numDupDigitsAtMostN(int N) {
            return 0;
        }
    }
}
