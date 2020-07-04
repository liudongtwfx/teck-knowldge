package algorithme.string;

import java.util.*;
import java.util.stream.Collectors;

public class RearrangeWordsInASentence {
    public static void main(String[] args) {
        new RearrangeWordsInASentence().bridge();
    }

    private void bridge() {
        String text = "Keep calm and code on";
        System.out.println(new Solution().arrangeWords(text));
    }

    class Solution {
        public String arrangeWords(String text) {
            text = text.substring(0, 1).toLowerCase() + text.substring(1);
            String[] strings = text.split(" ");
            Map<Integer, List<String>> wordsLenthToStrMap = new HashMap<>();
            for (String s : strings) {
                wordsLenthToStrMap.merge(s.length(), new ArrayList<>(Collections.singletonList(s)), (l1, l2) -> {
                    l1.addAll(l2);
                    return l1;
                });
            }
            String result = wordsLenthToStrMap.keySet().stream().sorted().map(wordsLenthToStrMap::get).flatMap(List::stream).collect(Collectors.joining(" "));
            return result.substring(0, 1).toUpperCase() + result.substring(1);
        }
    }
}
