package algorithme.backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1239. Maximum Length of a Concatenated String with Unique Characters
 * <p>
 * https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
 */
public class MaximumLengthOfaConcatenatedStringWithUniqueCharacters {
    public static void main(String[] args) {
        new MaximumLengthOfaConcatenatedStringWithUniqueCharacters().bridge();
    }

    private void bridge() {
        List<String> arr = Arrays.asList("un", "iq", "ue");
        System.out.println(new Solution().maxLength(arr));
    }

    class Solution {
        private int max;

        public int maxLength(List<String> arr) {
            boolean[] contains = new boolean[26];
            List<String> filter = new ArrayList<>();
            for (String s : arr) {
                boolean[] existed = new boolean[26];
                boolean dup = false;
                for (char c : s.toCharArray()) {
                    if (existed[c - 'a']) {
                        dup = true;
                        break;
                    }
                    existed[c - 'a'] = true;
                }
                if (!dup) {
                    filter.add(s);
                }
            }
            backtrace(filter, contains, 0);
            return max;
        }

        private void backtrace(List<String> arr, boolean[] contains, int start) {
            if (start == arr.size()) {
                return;
            }
            boolean has = false;
            for (char c : arr.get(start).toCharArray()) {
                if (contains[c - 'a']) {
                    has = true;
                    break;
                }
            }
            if (!has) {
                for (char c : arr.get(start).toCharArray()) {
                    contains[c - 'a'] = true;
                }
                int count = 0;
                for (boolean contain : contains) {
                    if (contain) {
                        count++;
                    }
                }
                max = Math.max(max, count);
                backtrace(arr, contains, start + 1);
                for (char c : arr.get(start).toCharArray()) {
                    contains[c - 'a'] = false;
                }
            }
            backtrace(arr, contains, start + 1);
        }
    }


    class Solution1 {
        public int maxLength(List<String> arr) {
            List<String> filter = new ArrayList<>();
            for (String s : arr) {
                boolean[] existed = new boolean[26];
                boolean dup = false;
                for (char c : s.toCharArray()) {
                    if (existed[c - 'a']) {
                        dup = true;
                        break;
                    }
                    existed[c - 'a'] = true;
                }
                if (!dup) {
                    filter.add(s);
                }
            }
            return check(arr, 0, 0, 0);
        }

        private int check(List<String> arr, int set, int pos, int sum) {
            if (pos == arr.size()) {
                return sum;
            }

            String curStr = arr.get(pos);
            int previousSet = set;

            for (int i = 0; i < curStr.length(); i++) {
                char c = curStr.charAt(i);
                if ((set >> (c - 'a') & 1) == 1) {
                    set = previousSet;//if this letter appears before, reverse the set
                    break;
                } else {
                    set |= 1 << (c - 'a');//append the letter to the right position
                }
            }


            if (set == previousSet) {//this word is not allowed to use, so just check the result without it
                return check(arr, set, pos + 1, sum);
            } else {//this word is allowed to use, then 2 senarios should be checked, with it or without it.
                return Math.max(check(arr, previousSet, pos + 1, sum), check(arr, set, pos + 1, sum + curStr.length()));
            }
        }
    }
}
