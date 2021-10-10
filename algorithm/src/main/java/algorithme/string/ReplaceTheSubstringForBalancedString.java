package algorithme.string;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReplaceTheSubstringForBalancedString {
    public static void main(String[] args) {
        new ReplaceTheSubstringForBalancedString().bridge();
    }


    private void bridge() {
        String s = "WWEQERQWQWWRWWERQWEQ";
        System.out.println(new Solution().balancedString(s));
    }

    class Solution {
        private final List<String> F = Arrays.asList("Q", "W", "E", "R");

        public int balancedString(String s) {
            Map<String, Long> map = Arrays.stream(s.split(""))
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            int x = s.length() / 4;
            System.out.println(map);
            return F.stream().map(s1 -> map.getOrDefault(s1, 0L)).mapToInt(n -> (int) Math.abs(n - x)).sum() / 2;
        }
    }
}
