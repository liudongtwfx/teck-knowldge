package algorithme;

public class MinimumLimitOfBallsInaBag1760 {
    public static void main(String[] args) {

    }

    class Solution {
        public boolean check(int[] nums, long cost, int maxOperations) {
            long ans = 0;
            for (int cur : nums) {
                if (cur % cost == 0) {
                    ans += cur / cost - 1;
                } else {
                    ans += cur / cost;
                }
            }
            return ans <= maxOperations;
        }

        public int minimumSize(int[] nums, int maxOperations) {
            long l = 1, r = 1000000000;
            long ret = 0;
            while (l <= r) {
                long mid = (l + r) / 2;
                if (check(nums, mid, maxOperations)) {
                    r = mid - 1;
                    ret = mid;
                } else {
                    l = mid + 1;
                }
            }
            return (int) ret;
        }
    }
}
