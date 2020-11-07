package algorithme.dp;

public class NumberOfSubArraysWithOddSum1524 {
    public static void main(String[] args) {
        new NumberOfSubArraysWithOddSum1524().bridge();
    }

    private void bridge() {
        int[] arr = {100, 100, 99, 99};
        int i = new Solution().numOfSubarrays(arr);
        System.out.println(i);
    }

    class Solution {
        private static final int MOD = 1000000007;

        public int numOfSubarrays(int[] arr) {
            int len = arr.length;
            int oddSumCounts = 0;
            int ans = 0, sum = 0;
            for (int i = 0; i < len; i++) {
                sum += arr[i];
                if (sum % 2 != 0) {
                    ans = (ans + i + 1 - oddSumCounts) % MOD;
                    oddSumCounts++;
                } else {
                    ans = (ans + oddSumCounts) % MOD;
                }
            }
            return ans;
        }
    }
}
