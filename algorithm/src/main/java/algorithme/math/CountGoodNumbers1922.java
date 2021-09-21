package algorithme.math;

public class CountGoodNumbers1922 {
    public static void main(String[] args) {
        for (long i = 0; i < 200; i++) {
            if (new Solution().countGoodNumbers(i) != new CorrectSolution().countGoodNumbers(i)) {
                System.out.println(i);
            }
        }
    }

    static class Solution {
        private static final long MOD = 1_000_000_007;

        private static long dfs(int num, long count) {
            // System.out.println(count);
            if (count == 0) {
                return 1;
            }
            if (count == 1) {
                return num;
            }
            long extra = count % 2 == 0 ? 1 : num;
            long left = dfs(num, count / 2);
            // System.out.println("left" + left);
            return ((left * left) % MOD * extra) % MOD;
        }

        public int countGoodNumbers(long n) {
            long twenty = n / 2;
            long four = dfs(20, twenty) % MOD;
            long five = (n % 2 == 0 ? 1 : 5);
            return (int) ((four * five) % MOD);
        }
    }

    static class CorrectSolution {
        private static final long MOD = 1_000_000_007;

        private long dfs(int num, long count) {
            if (count == 0) {
                return 1;
            }
            if (count == 1) {
                return num;
            }
            int extra = count % 2 == 0 ? 1 : num;
            long left = dfs(num, count / 2) % MOD;
            return (left * left * extra) % MOD;
        }

        public int countGoodNumbers(long n) {
            long fourCount = n / 2;
            long fiveCount = n - n / 2;
            long four = dfs(4, fourCount);
            long five = dfs(5, fiveCount);
            return (int) ((four * five) % MOD);
        }
    }
}
