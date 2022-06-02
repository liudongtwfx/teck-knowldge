package algorithme;

public class InputUtils {
    public static void main(String[] args) {
        Solution solution = new Solution();
        for (int i = 0; i < 100; i++) {
            String binary = Integer.toBinaryString(i);
            System.out.println(binary + " " + solution.numberOfSteps(i));
        }
    }

    static class Solution {
        public int numberOfSteps(int num) {
            int ans = 0;
            while (num > 0) {
                ans++;
                if (num % 2 == 0) {
                    num /= 2;
                } else {
                    num -= 1;
                }
            }
            return ans;
        }
    }
}
