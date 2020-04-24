package algorithme.backtrace;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 1079. Letter Tile Possibilities
 * https://leetcode.com/problems/letter-tile-possibilities/
 */
public class LetterTilePossibilities {

    public static void main(String[] args) {
        new LetterTilePossibilities().bridge();
    }

    private void bridge() {
        int aab = new Solution().numTilePossibilities("AAB");
        System.out.println(aab);
    }

    class Solution {
        public int numTilePossibilities(String tiles) {
            Set<String> result = new HashSet<>();
            for (char c : tiles.toCharArray()) {
                Set<String> copy = new HashSet<>(result);
                for (String s : copy) {
                    for (int i = 0; i < s.length(); i++) {
                        result.add(s.substring(0, i) + c + s.substring(i));
                    }
                    result.add(s + c);
                }
                result.add(String.valueOf(c));
            }
            return result.size();
        }
    }


    class Solution1 {
        int count = 0;

        public int numTilePossibilities(String tiles) {
            char[] letters = tiles.toCharArray();
            Arrays.sort(letters);
            generate(letters, new boolean[letters.length], 0);
            return count;
        }

        private void generate(char[] letters, boolean[] visited, int idx) {
            for (int i = 0; i < letters.length; i++) {
                if (visited[i]) {
                    continue;
                }
                if (i > 0 && (letters[i - 1] == letters[i]) && !visited[i - 1]) {
                    continue;
                }
                count++;
                visited[i] = true;
                generate(letters, visited, idx + 1);
                visited[i] = false;
            }
        }
    }
}

