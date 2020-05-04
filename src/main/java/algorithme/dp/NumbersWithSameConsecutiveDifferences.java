package algorithme.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 967. Numbers With Same Consecutive Differences
 * https://leetcode.com/problems/numbers-with-same-consecutive-differences/
 *
 * @author liudong
 */
public class NumbersWithSameConsecutiveDifferences {
    public static void main(String[] args) {
        new NumbersWithSameConsecutiveDifferences().bridge();
    }

    private void bridge() {
        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int length = new Solution().numsSameConsecDiff(i, j).length;
                System.out.println("i:" + i + ",j :" + j + " len:" + length);
            }
        }
    }

    class Solution {
        public int[] numsSameConsecDiff(int N, int K) {
            List<Integer> dp = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                dp.add(i);
            }
            for (int i = 1; i < N; i++) {
                List<Integer> last = dp;
                List<Integer> newLine = new ArrayList<>();
                for (Integer s : last) {
                    if (s == 0) {
                        continue;
                    }
                    int n = s % 10;
                    if (n + K < 10) {
                        newLine.add(s * 10 + n + K);
                    }
                    if (K != 0 && n - K >= 0) {
                        newLine.add(s * 10 + n - K);
                    }
                }
                dp = newLine;
            }
            return dp.stream().mapToInt(Integer::valueOf).toArray();
        }
    }
}
