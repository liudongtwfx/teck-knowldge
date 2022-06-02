package algorithme.stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RemoveAllAdjacentDuplicatesInStringII1209 {
    public static void main(String[] args) {
        new RemoveAllAdjacentDuplicatesInStringII1209().bridge();
    }


    private void bridge() {
        String s = "deeedbbcccbdaa";
        int k = 3;
        LinkedList<Integer> linkedList=new LinkedList<>();
        System.out.println(new Solution().removeDuplicates(s, k));
    }

    class Solution {
        public String removeDuplicates(String s, int k) {
            List<Character> characters = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            int lastMax = 0;
            for (int i = 0; i < s.length(); i++) {
                lastMax = dequeFromK(characters, k, s.charAt(i), lastMax);
            }
            for (Character character : characters) {
                sb.append(character);
            }
            return sb.toString();
        }


        private int dequeFromK(List<Character> character, int k, char c, int lastMax) {
            if (character.isEmpty() || c != character.get(character.size() - 1)) {
                character.add(c);
                return 1;
            }
            if (lastMax + 1 < k) {
                character.add(c);
                return lastMax + 1;
            }
            for (int i = 0; i < k - 1; i++) {
                character.remove(character.size() - 1);
            }
            if (character.isEmpty()) {
                return 0;
            }
            char last = character.get(character.size() - 1);
            int ans = 0;
            for (int i = character.size() - 1; i >= 0; i--) {
                if (character.get(i) != last) {
                    return character.size() - i;
                }
                ans++;
            }
            return ans;
        }
    }
}
