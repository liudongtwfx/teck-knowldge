package algorithme.string;

import java.util.HashMap;
import java.util.Map;

public class CountSubstringsThatDifferByOneCharacter {
    public static void main(String[] args) {

    }

    class Solution {
        public int countSubstrings(String s, String t) {
            Map<String, Integer> subStrCountMap = new HashMap<>();
            for (int i = 0; i < t.length(); i++) {
                for (int j = i + 1; j < t.length(); j++) {
                    String sub = t.substring(i, j);
                    subStrCountMap.put(sub, subStrCountMap.getOrDefault(sub, 0) + 1);
                }
            }
            int ans = 0;
            for (int i = 0; i < s.length(); i++) {
                for (int j = 0; j <= i; j++) {
                    int index = s.charAt(j) - 'a';
                    for (int m = 0; m < j - i + 1; m++) {
                        for (int k = 0; k < 26; k++) {
                            if (k == index) {
                                continue;
                            }
                            String diffSub = s.substring(i, i + m) + (char) ('a' + k) + s.substring(m + i, j);
                            int count = subStrCountMap.getOrDefault(diffSub, 0);
                            if (count != 0) {
                                System.out.println(diffSub);
                            }
                            ans += count;
                        }
                    }
                }
            }
            return ans;
        }
    }
}
