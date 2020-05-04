package algorithme.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 1218. Longest Arithmetic Subsequence of Given Difference
 * https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/
 *
 * @author liudong
 */
public class LongestArithmeticSubsequenceofGivenDifference {
    public static void main(String[] args) {

    }

    class Solution {
        public int longestSubsequence(int[] arr, int difference) {
            Map<Integer, Integer> dp = new HashMap<>();
            int ans = 1;
            for (int i : arr) {
                int value = dp.getOrDefault(i - difference, 0);
                dp.put(i, value + 1);
                ans = Math.max(ans, value + 1);
            }
            return ans;
        }
    }
}
