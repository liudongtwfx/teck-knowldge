package algorithme;

public class MinimumNumberOfOperationsToMoveAllBallsToEachBox1769 {
    public static void main(String[] args) {

    }

    class Solution {
        public int[] minOperations(String boxes) {
            int[] res = new int[boxes.length()];
            char[] chars = boxes.toCharArray();
            for (int i = 0; i < boxes.length(); i++) {
                if (chars[i] == '0') {
                    continue;
                }
                for (int one = 0; one < boxes.length(); one++) {
                    res[one] += Math.abs(one - i);
                }
            }
            return res;
        }
    }
}
