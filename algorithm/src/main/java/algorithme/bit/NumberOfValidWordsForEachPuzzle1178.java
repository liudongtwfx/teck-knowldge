package algorithme.bit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author liudong
 */
public class NumberOfValidWordsForEachPuzzle1178 {

    public static void main(String[] args) {
        new NumberOfValidWordsForEachPuzzle1178().bridge();
    }

    private void bridge() {
        System.out.println(new Solution().getBitCountList("abcdefg"));
        String[] words = {"aaaa", "asas", "able", "ability", "actt", "actor", "access"};
        String[] puzzle = {"aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"};
        System.out.println(new Solution().findNumOfValidWords(words, puzzle));
    }

    class Solution {
        public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
            List<Integer> ans = new ArrayList<>();
            Map<Integer, Integer> map = Arrays.stream(words).map(this::getBitCount)
                    .collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum));
            for (String puzzle : puzzles) {
                List<Integer> bitCount = getBitCountList(puzzle);
                int count = bitCount.stream()
                        .map(c -> map.getOrDefault(c, 0))
                        .mapToInt(Integer::valueOf)
                        .sum();
                ans.add(count);
            }
            return ans;
        }

        private List<Integer> getBitCountList(String puzzle) {
            List<Integer> result = new ArrayList<>();
            char[] chars = puzzle.toCharArray();
            for (int i = 64; i <= 127; i++) {
                int res = 0;
                for (int j = 0; j < 7; j++) {
                    if ((i & (1 << j)) != 0) {
                        res |= (1 << (chars[6 - j] - 'a'));
                    }
                }
                result.add(res);
            }
            return result;
        }

        private int getBitCount(String s) {
            int ans = 0;
            char[] chars = s.toCharArray();
            for (char aChar : chars) {
                ans |= (1 << (aChar - 'a'));
            }
            return ans;
        }
    }
}
