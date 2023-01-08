package algorithme.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortCharactersByFrequency451 {
    public static void main(String[] args) {
        new SortCharactersByFrequency451().bridge();
    }

    private void bridge() {
        String s = "tree";
        System.out.println(new Solution().frequencySort(s));
    }

    class Solution {
        public String frequencySort(String s) {
            int[] charCount = new int[256];
            for (char c : s.toCharArray()) {
                charCount[c]++;
            }
            List<int[]> countList = new ArrayList<>();
            for (int i = 0; i < 256; i++) {
                countList.add(new int[]{i, charCount[i]});
            }
            countList.sort(Comparator.comparingInt(a -> a[1]));
            StringBuilder sb = new StringBuilder();
            for (int i = 255; i >= 0; i--) {
                if (countList.get(i)[1] == 0) {
                    break;
                }
                char[] chars = new char[countList.get(i)[1]];
                Arrays.fill(chars, (char) (countList.get(i)[0]));
                sb.append(chars);
            }
            return sb.toString();
        }
    }
}
