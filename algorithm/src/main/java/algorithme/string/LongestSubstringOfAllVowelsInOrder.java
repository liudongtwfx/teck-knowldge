package algorithme.string;

import org.springframework.util.StopWatch;

public class LongestSubstringOfAllVowelsInOrder {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 50000; i++) {
            sb.append('a');
        }
        sb.append("eiou");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int ans = new Solution().longestBeautifulSubstring(sb.toString());
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        System.out.println(ans);
    }

    static class Solution {

        private static final char[] VOWS = {'a', 'e', 'i', 'o', 'u'};

        public int longestBeautifulSubstring(String word) {
            int currentVowelIndex = -1;
            int start = 0, ans = 0;
            for (int i = 0; i < word.toCharArray().length; i++) {
                char c = word.charAt(i);
                int index = indexOfVowel(c);
                if (index - currentVowelIndex == 0) {
                    if (index == 4) {
                        ans = Math.max(ans, i + 1 - start);
                    }
                    continue;
                }
                if (c == 'a') {
                    start = i;
                    currentVowelIndex = 0;
                    continue;
                }
                if (index - currentVowelIndex == 1) {
                    if (index == 4) {
                        ans = Math.max(ans, i + 1 - start);
                    }
                    currentVowelIndex = index;
                    continue;
                }
                currentVowelIndex = -1;
            }
            return ans;
        }

        private static int indexOfVowel(char vowel) {
            if (vowel == 'a') {
                return 0;
            }
            if (vowel == 'e') {
                return 1;
            }
            if (vowel == 'i') {
                return 2;
            }
            if (vowel == 'o') {
                return 3;
            }
            if (vowel == 'u') {
                return 4;
            }
            return -1;
        }
    }
}
