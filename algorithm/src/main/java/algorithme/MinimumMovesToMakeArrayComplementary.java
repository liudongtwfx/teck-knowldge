package algorithme;

import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Integer.min;

public class MinimumMovesToMakeArrayComplementary {
    public static void main(String[] args) {
        AtomicReference<String> reference = new AtomicReference<>();
        boolean abc = reference.compareAndSet("12", "abc");
        System.out.println(abc);
    }

    class Solution {
        public int minMoves(int[] nums, int limit) {
            int[] dT = new int[2 * limit + 1];   // dT[i] = T[i] - T[i-1]

            int n = nums.length, res = Integer.MAX_VALUE;
            for (int i = 0; i < n / 2; ++i) {
                int a = nums[i], b = nums[n - 1 - i];

                dT[2] += 2;
                dT[Math.min(a, b) + 1] -= 1;
                dT[a + b] -= 1;
                dT[a + b + 1] += 1;
                dT[Math.max(a, b) + limit + 1] += 1;
            }

            int curr = 0;
            for (int t = 2; t <= limit * 2; ++t) {
                curr += dT[t];
                res = min(res, curr);
            }
            return res;
        }
    }
}
