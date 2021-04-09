package algorithme.greedy;

public class ChangeMinimumCharactersToSatisfyOneOfThreeConditions1737 {
    public static void main(String[] args) {

    }

    class Solution {
        public int minCharacters(String a, String b) {
            System.out.println("");
            return 0;
        }

        private int[] getDict(String s) {
            int[] res = new int[26];
            for (char c : s.toCharArray()) {
                res[c - 'a']++;
            }
            for (int i = 1; i < res.length; i++) {
                res[i] += res[i - 1];
            }
            return res;
        }
    }
}
