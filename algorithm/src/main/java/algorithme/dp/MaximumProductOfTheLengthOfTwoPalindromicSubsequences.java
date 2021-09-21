package algorithme.dp;

import java.util.ArrayList;
import java.util.List;

public class MaximumProductOfTheLengthOfTwoPalindromicSubsequences {
    public static void main(String[] args) {
        new MaximumProductOfTheLengthOfTwoPalindromicSubsequences().bridge();
    }

    private void bridge() {
        Solution solution = new Solution();
        String s = "leetcodecom";
        System.out.println(solution.maxProduct(s));
    }

    class Solution {
        private int value;

        public int maxProduct(String s) {
            dfs(s, 0, new boolean[s.length()], "");
            return value;
        }

        private void dfs(String s, int index, boolean[] contains, String current) {
            if (index >= s.length()) {
                return;
            }
            contains[index] = true;
            current += s.charAt(index);
            if (isPalindrome(current)) {
                String remains = getRemainString(s, contains);
                List<String> remainPalindromes = new ArrayList<>();
                dfsAllPalindromes(remains, 0, "", remainPalindromes);
                int maxSize = remainPalindromes.stream().map(String::length).max(Integer::compare).orElse(1);
                // System.out.printf("%s %s %s\n", value, current, String.join(",", remainPalindromes));
                value = Math.max(value, current.length() * maxSize);
            }
            dfs(s, index + 1, contains, current);
            contains[index] = false;
            current = current.substring(0, current.length() - 1);
            dfs(s, index + 1, contains, current);
        }

        private void dfsAllPalindromes(String s, int index, String current, List<String> palindromes) {
            if (index >= s.length()) {
                return;
            }
            current += s.charAt(index);
            dfsAllPalindromes(s, index + 1, current, palindromes);
            if (isPalindrome(current)) {
                System.out.printf("%s %s\n", s, current);
                palindromes.add(current);
            }
            current = current.substring(0, current.length() - 1);
            dfsAllPalindromes(s, index + 1, current, palindromes);
        }

        private String getRemainString(String s, boolean[] contains) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (!contains[i]) {
                    sb.append(s.charAt(i));
                }
            }
            return sb.toString();
        }

        private boolean isPalindrome(String s) {
            for (int i = 0, j = s.length() - 1; i <= j; i++, j--) {
                if (s.charAt(i) != s.charAt(j)) {
                    return false;
                }
            }
            return true;
        }
    }

    class SolutionQuick {
        public int maxProduct(String s) {
            // max possible length is 12.
            // each index in mask is a binary selection of the chars in s
            // the value in mask is the length of the palindrome
            int[] mask = new int[(int) Math.pow(2, 12)]; //4096

            int result = 0;

            // compute the length of the all possible char selections
            for (int i = 0; i < (1 << s.length()); i++) {
                mask[i] = palSize(s, i);
            }

            // compute the max product of length
            int allSelected = (1 << s.length()) - 1;
            for (int i = 1; i <= allSelected; i++) {
                if (mask[i] > 0 && mask[i] * (s.length() - mask[i]) > result) {
                    int inverse = allSelected ^ i;
                    for (int j = inverse; j > 0; j = (j - 1) & inverse) {
                        // there is no common index for the two candidates
                        result = Math.max(result, mask[i] * mask[j]);
                        
                    }
                }
            }

            return result;

        }

        private int palSize(String s, int mask) {
            int p1 = 0, p2 = s.length(), result = 0;

            while (p1 <= p2) {

                if ((mask & (1 << p1)) == 0) {
                    // find the right most selected char
                    p1++;
                } else if ((mask & (1 << p2)) == 0) {
                    // find the left most selected char
                    p2--;
                } else if (s.charAt(p1) != s.charAt(p2)) {
                    // not a palinfrome
                    return 0;
                } else {
                    // move forward
                    result = p1++ == p2-- ? result + 1 : result + 2;

                }
            }

            return result;
        }
    }
}
