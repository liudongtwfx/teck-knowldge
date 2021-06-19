package algorithme.string;

import org.springframework.util.StopWatch;

public class LongestSubstringOfAllVowelsInOrder {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 500000; i++) {
            sb.append('a');
        }
        sb.append("eiou");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int ans = new MySolution().longestBeautifulSubstring(sb.toString());
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        System.out.println(ans);
    }

    static class Solution {
        public int longestBeautifulSubstring(String word) {
            int maxCount = 0;
            int letterChanged = 0;
            int count = 1;
            char[] arr = word.toCharArray();
            for (int i = 1; i < arr.length; i++) {
                System.out.println(i);
                if (arr[i] >= arr[i - 1]) {
                    count++;
                    if (arr[i] != arr[i - 1]) {
                        letterChanged++;
                    }
                } else {
                    if (arr[i] < arr[i - 1] && letterChanged == 4) {
                        maxCount = Math.max(maxCount, count);
                    }
                    letterChanged = 0;
                    count = 1;
                }

            }
            return letterChanged == 4 ? Math.max(maxCount, count) : maxCount;
        }
    }

    static class MySolution {
        public int longestBeautifulSubstring(String word) {
            long startC = System.currentTimeMillis();
            int currentVowelIndex = -1;
            int start = 0, ans = 0;
            long totalMs = 0;
            char[] arr = word.toCharArray();
            for (int i = 0; i < arr.length; i++) {
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
            System.out.println(totalMs);
            System.out.println(System.currentTimeMillis() - startC);
            return ans;
        }

        private int indexOfVowel(char vowel) {
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
