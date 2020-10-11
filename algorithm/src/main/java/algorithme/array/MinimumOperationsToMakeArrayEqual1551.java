package algorithme.array;

public class MinimumOperationsToMakeArrayEqual1551 {
    public static void main(String[] args) {
        new MinimumOperationsToMakeArrayEqual1551().bridge();
    }

    private void bridge() {
        Solution solution = new Solution();
        System.out.println(solution.minOperations(6));
    }

    static class Solution {
        public int minOperations(int n) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                count += (i * 2 + 1);
                // System.out.println(i * 2 + 1);
            }

            int each = count / n;
            int ans = 0;
            for (int i = 0; i < n; i++) {
                if ((i * 2 + 1) < each) {
                    ans += (each - (i * 2 + 1));
                    continue;
                }
                return ans;
            }
            return ans;
        }
    }
}
