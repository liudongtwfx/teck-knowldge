package algorithme.string;

public class LargestMergeOfTwoStrings1754 {
    public static void main(String[] args) {
        new LargestMergeOfTwoStrings1754().bridge();
    }

    private void bridge() {
        String word1 = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeqeeeeeeeeeeeeqeeeeeeeeeeeeeeeqeeeeeeeqeqeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeqeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeee";
        String word2 = "eeeqeeeeeeqeeeeeeeeeeeeqqeeqeeqqeeeqeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeeeeqeqeeeqeeeqeeeeeeeeeeeqeeeeqeqeeqeqeeeeeqeeeqeeqeqeeeeeeeqeeqeqeeeeeeeeqeqeqeeeqeeeeqeeeeqeeeqeeeqeeeeeeqeeeeeeeeeeeeqeeeeeeeeeeeeeeeeeqeeeeeeqeqqeqeeeeeeeeeqqeeeqeqeeqeeeeeeeeeqeeeeqeeeqeeeeeqeeqeeeqqeeeeeeeeqeeeeeeeeeeeeeeqqeeqqqeeeqeeeeeeeeqeeqeeeeeqeqqeqeeqqeeeeeeeeeeqeqeqeeeeeqeeeeeeeeeqeqeeeeeeqqeeeqeeeeqeeeeeeqeeqeeeeqeeeeeeeqeeqeeeeqeeqeeeeeeeeeeeqqeeeeeeeqeeeeeqeeqeeeeeeqeqeeeqeeeeeeeeqeeeeeqeeeeeeeeeeqeqeeeeqqqeeeeeeqqqeeeeeqeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeqeeqqeeqeeeeeeeeeeeeeeqeeqqeeeqeeeeeeeeeeeqeeeeqeeeeeeeeeeeeqeeeeeqeeeeeeeeeeeqeqqeqqeeeeeeeeeeqqeeeeeeeeeqeeeeeeeeeeeeeeeeeeeqeeqqeeqeeeeeeqeeqeeeqeeeeeeqeeeeqeeqeqqqeeeqeeeeeeeeqqeeqeeeeqeeeeqeeeeeeeeeqqeeqqeeeeeeqeeeeeeeeqqeeeeeeeqeeeeeeeeeeeeqeeqeeeeeeeeeqeqeeqeeeeeeeeeeqeeqeeeeeeqeeeqeeeeeeeeeqqeeqqeeeeeqeeeqeeeeeeeeeeeeeeeeqeqeeeeeeqeeqeeeqeeeqeeqeeqeeeeeeqqeeeeeeeeeqqqeqqeeeqeeeeqqeeqqqeeeqeeeeqeeeeeeeeqeeeeqqqeeeeqeeeeqeqqeeqeeeeeeeeeeqeeeeeqqqeeqeeeeeeeeeeeqeeeeqeeeeeeeeeeqeeeqeeqeeeeeeqqeqeqqeeeeeeeeeeeeeeeeeeeeqeeeeeeeeeqqeeeeeeeeeeeeeqqeqeqeeeeeeqeeeeqqqeeeqqeqqeeeeeeeeeeeeeeeeeeeeeeeeeeeqeeeeeeqeqeeeeeeeeeeeeeeeeeqqqeeqeeeeqeeeeeqqeeeeeeeqeeeeeqqeeqqqqeeeeeeeeeeeqeeeeeeeeeeqeeqeeqeeeeeeeeeeeeqeqeeqeeeeeqqeeeeqqeeeqqeeeeeeeeeeqeeeqqeeeqeeeeeeqeeqqeeeqeqqeeeqeqeqqeeeqeeeeeeeqeeeqeeeeeeqqeeqeeqeeeeeqeeeeqeeqeeqeqeeeeeeqeeeeqeeqeeeeeqeeqeqeeeeeeqeeeeeeeeeeeeeeqeeqeeqeeeeeeeqeeqeeeeeeeqeeeeeeeeeeeeeqqeeeeeeqeeeeqeeqeqeeeeeeeeqeqeqeeeeeeeqeqeeeeeeeeqeeeqeeeeeeeeeeeeeeqeeqeeeeeeeqeeeeeeeeeeeqqeeeqeqqeeeeeeeqeeeeeqeeqeeeeqeeqeeeqeeeeeqqeeqeeeqeeeeeqeeeeeqeqeeqqeeeeqeeeeeeeeeeeeeeeeeqeqeeeeqeeqeqeqeeeeeeeeqeeeeeqqeeeqeeeeqqeeeeeeqeeeeeeqeeeqeeeeeeeeeeqqeeeeeeeeqeeeqeeqeeeeeeqeeqeeeeeeeeqeqeeeeeeeeeeqeeeeeeeeqeqeqqeeeqeeeeqeeeeeeqeeeeqeeeqeeeeeeeeqeqqqqeqeeeeeqeeeqeeeeeeeqeqeeqeeeqeeeqeeeqeeeeeeqqqeqeeqeeeeeeqeqeqeeeeeeeeeqeqeeqeeqeeeeeeeeeqeeeeeeeeeeeeeeeeeeeeeeqqqeeeeeeqeqeqeeqqeeeqqeeeqeeeeeqeeqeqeeqeeeeeeeeqeeeqeeeeeeeqeeeeeqeqeqeqeqeeeeeeeeqeeeeqqqqeeeeqeeeeeeeeeeqeeqeeeqeeeqeeeeqeeeeeeqqqqeeqeeqeeeeeeqqeeeeeeeeeqeqqeeeeeeqeeeeeqeqeeqqeeqqeeeeeeeqqqeeeeeeeeeqeeeeeqeqeeeeeeeqqqqqeeqeeqeeeeqeeeeeqeeeeeeeeqeqeeeeeeeeeeqeeqeeeeeeeeeeeeqqeeeqqeeeeeeeeeeqeqeeeqeeeeeeeqeeeeeeeeeeeeeeeqeeeeeeqqeeqeqeeeeeeeqeqeqqeeeqeeeqeeeqeeeeeqqeeeeeeqeeqqeeeeeeeeqeeeeeqeeeeeeeeeeeeeeeqeeeeqqeeeeeqeeqeeeeqqeeeeeeeqeeeeqqeeeqeqeqeeeeeqeeeeeeqeqeeeeqeeeeqeeeeqeeqeeeeqqeeeeeeeqeeeeeeeeeqeqeeeeeeeeeqeqeeeeeqeqeqqeqeqeeeqeeeqeqeqqqqqeeqeeeeqeeeqeeeqeeqeeeqeeeeeeqqeeeqeeeeeeeeeeeeeeqeqeeqeeeeqeeqeeeqqeeeeeeeeeeeeeqeeqeeeeeeeeeqeeeqqeeeeeeeeeeqeeeeeqeeeeeeeqeeeeeeeeeeeeeeeeqeeeeee";
        System.out.println(new Solution().largestMerge(word1, word2));
    }

    class Solution {
        public String largestMerge(String word1, String word2) {
            int i = 0;
            int j = 0;
            StringBuilder sb = new StringBuilder();
            while (i < word1.length() && j < word2.length()) {
                if (word1.charAt(i) > word2.charAt(j)) {
                    sb.append(word1.charAt(i));
                    i++;
                } else if (word1.charAt(i) < word2.charAt(j)) {
                    sb.append(word2.charAt(j));
                    j++;
                } else {
                    String s1 = word1.substring(i);
                    String s2 = word2.substring(j);
                    if (s1.compareTo(s2) >= 0) {
                        sb.append(word2.charAt(j));
                        i++;
                    } else {
                        sb.append(word2.charAt(j));
                        j++;
                    }
                }
            }
            if (i < word1.length()) {
                sb.append(word1.substring(i));
            }
            if (j < word2.length()) {
                sb.append(word2.substring(j));
            }
            return sb.toString();
        }
    }
}
