package algorithme.backtracing;

import java.util.ArrayList;
import java.util.List;

/**
 * 306. Additive Number
 * https://leetcode.com/problems/additive-number/
 */
public class AdditiveNumber {
    public static void main(String[] args) {
        new AdditiveNumber().bridge();
    }

    private void bridge() {
        System.out.println(new Solution().isAdditiveNumber("199001200"));
    }

    class Solution {
        public boolean isAdditiveNumber(String num) {
            return backTrace(num, 0);
        }

        private boolean backTrace(String str, int start) {
            if (start == str.length()) return true;
            for (int i = 0; i < str.length(); i++) {
                for (int j = i + 1; j < str.length() - 1; j++) {
                    List<String> addedNumber = new ArrayList<>();
                    addedNumber.add(str.substring(0, i + 1));
                    addedNumber.add(str.substring(i + 1, j + 1));

                    String value = trimZero(addedNumber.get(0)) + trimZero(addedNumber.get(1));
                    while (value.length() < str.length()) {
                        addedNumber.add(addTwoStr(addedNumber.get(addedNumber.size() - 2), addedNumber.get(addedNumber.size() - 1)));
                        value += trimZero(addedNumber.get(addedNumber.size() - 1));
                    }
                    if (value.equals(str)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private String trimZero(String s) {
            if (s.equals("0")) return s;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != '0') return s.substring(i);
            }
            return "";
        }

        private String addTwoStr(String a, String b) {
            int al = a.length() - 1;
            int bl = b.length() - 1;
            int extra = 0;
            String ans = "";
            while (al >= 0 && bl >= 0) {
                int added = a.charAt(al) - '0' + (b.charAt(bl) - '0') + extra;
                ans = (added % 10) + ans;
                extra = added / 10;
                al--;
                bl--;
            }
            while (al >= 0) {
                int added = a.charAt(al) - '0' + extra;
                ans = (added % 10) + ans;
                extra = added / 10;
                al--;
            }

            while (bl >= 0) {
                int added = b.charAt(bl) - '0' + extra;
                ans = (added % 10) + ans;
                extra = added / 10;
                bl--;
            }
            return extra == 0 ? ans : extra + ans;
        }
    }
}
