package algorithme.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 842. Split Array into Fibonacci Sequence
 * https://leetcode.com/problems/split-array-into-fibonacci-sequence/
 *
 * @author liudong
 */
public class SplitArrayIntoFibonacciSequence {
    public static void main(String[] args) {

    }

    private static class Solution1 {
        public List<Integer> splitIntoFibonacci(String S) {
            List<Integer> result = new ArrayList<>();
            backtrace(S, result, 0);
            return result;
        }

        private boolean backtrace(String s, List<Integer> result, int start) {
            if (start == s.length()) {
                return result.size() > 2;
            }
            int num = 0;
            for (int i = start; i < s.length(); i++) {
                num = num * 10 + (s.charAt(i) - '0');
                if (num < 0) {
                    return false;
                }
                if (!String.valueOf(num).equals(s.substring(start, i + 1))) {
                    return false;
                }
                if (result.size() < 2 || result.get(result.size() - 2) + result.get(result.size() - 1) == num) {
                    result.add(num);
                    if (backtrace(s, result, i + 1)) {
                        return true;
                    }
                    result.remove(result.size() - 1);
                }
            }
            return false;
        }
    }

    class Solution {
        public List<Integer> splitIntoFibonacci(String s) {
            for (int i = 0; i < s.length(); i++) {
                for (int j = i + 1; j < s.length(); j++) {
                    List<Integer> result = new ArrayList<>();
                    try {
                        Integer one = Integer.parseInt(s.substring(0, i + 1));
                        Integer two = Integer.parseInt(s.substring(i + 1, j));
                        result.add(one);
                        result.add(two);
                        StringBuilder mock = new StringBuilder(one + String.valueOf(two));
                        while (mock.length() < s.length()) {
                            int i1 = result.get(result.size() - 2) + result.get(result.size() - 1);
                            result.add(i1);
                            mock.append(i1);
                        }
                        if (mock.toString().equals(s)) {
                            return result;
                        }
                    } catch (NumberFormatException nfe) {
                        continue;
                    }
                }
            }
            return Collections.emptyList();
        }
    }
}
