package algorithme.string;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DetermineIfTwoStringsAreClose {
    public static void main(String[] args) {
        new DetermineIfTwoStringsAreClose().bridge();
    }

    private void bridge() {
        String word1 = "cpgafjlfbclfynlfjjrmbugfkqfvzcifafslfcfftfcujfvqzcolzfqycwfclqhfjeyflfmfqclcxdl";
        String word2 = "nmcuxwytmpncucacczbomfacjeonpzdufmccccnuclcnehcmacavamemumccgkgscqiumimuwuccuic";
        System.out.println(sortedStr(word1));
        System.out.println(sortedStr(word2));
        System.out.println(word1.length());
        System.out.println(new Solution().closeStrings(word1, word2));
    }

    class Solution {
        public boolean closeStrings(String word1, String word2) {
            if (word1.length() != word2.length()) {
                return false;
            }

            int[] charCount = new int[26];
            for (char c : word1.toCharArray()) {
                charCount[c - 'a']++;
            }

            int[] charCount2 = new int[26];
            for (char c : word2.toCharArray()) {
                charCount2[c - 'a']++;
            }

            int count = 0;
            for (int i = 0; i < 26; i++) {
                if (charCount[i] == 0) {
                    continue;
                }
                boolean exist = false;
                for (int j = 0; j < 26; j++) {
                    if (charCount[i] == charCount2[j] && charCount[j] != 0 && charCount2[i] != 0) {
                        count += charCount[j];
                        charCount[j] = 0;
//                        char c1 = (char) ('a' + i);
//                        char c2 = (char) ('a' + j);
//                        System.out.println(c1 + "    " + c2);
                        exist = true;
                        break;
                    }
                }
                if (!exist) {
                    return false;
                }
            }
            System.out.println(count);
            return true;
        }
    }

    private String sortedStr(String origin) {
        return Arrays.stream(origin.split("")).sorted().collect(Collectors.joining(""));
    }
}
