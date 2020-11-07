package algorithme.backtrace;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 1291. Sequential Digits
 * https://leetcode.com/problems/sequential-digits/
 */
public class SequentialDigits {

    class Solution {

        private List<Integer> generateAll(int high) {
            List<Integer> result = new ArrayList<>();
            List<List<String>> list = new ArrayList<>();
            List<String> one = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
            list.add(one);
            for (int i = 0; i < 9; i++) {
                List<String> last = list.get(list.size() - 1);
                List<String> newList = new ArrayList<>();
                for (String s : last) {
                    char c = s.charAt(s.length() - 1);
                    if (c == '9') {
                        continue;
                    }
                    int n = c - '0' + 1;
                    Integer value = Integer.valueOf(s + n);
                    if (value > high) {
                        continue;
                    }
                    newList.add(s + n);
                    result.add(value);
                }
                list.add(newList);
            }
            return Collections.unmodifiableList(result);
        }

        public List<Integer> sequentialDigits(int low, int high) {
            List<Integer> result = generateAll(high);
            return result.stream().filter(i -> i >= low && i <= high).sorted().collect(Collectors.toList());
        }
    }
}
