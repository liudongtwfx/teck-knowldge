package algorithme;

public class CheckIfNumberIsASumOfPowersOfThree1780 {
    public static void main(String[] args) {
        new CheckIfNumberIsASumOfPowersOfThree1780().bridge();
    }

    private void bridge() {
        Solution solution = new Solution();
        boolean b = solution.checkPowersOfThree(91);
        System.out.println(b);
    }

    static class Solution {

        static int[] tmp = new int[10];

        static {
            int v = 1;
            for (int i = 0; i < 10; i++) {
                tmp[i] = v;
                v *= 3;
            }
        }

        public boolean checkPowersOfThree(int n) {
            for (int i = 9; i >= 0; i--) {
                if (tmp[i] <= n) {
                    n -= tmp[i];
                }
                System.out.println(n);
            }
            return n == 0;
        }
    }
}
