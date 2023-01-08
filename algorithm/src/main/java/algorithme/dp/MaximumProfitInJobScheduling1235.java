package algorithme.dp;

import java.util.Arrays;
import java.util.Comparator;

public class MaximumProfitInJobScheduling1235 {
    public static void main(String[] args) {

    }

    class Solution {
        public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
            int n = profit.length;
            Job[] jobs = new Job[n];
            for (int i = 0; i < n; i++) {
                jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
            }

            Arrays.sort(jobs, Comparator.comparingInt(a -> a.end));              // sorting based on end time

            int[] dp = new int[n];
            dp[0] = jobs[0].profit;

            for (int i = 1; i < n; i++) {

                dp[i] = Math.max(dp[i - 1], jobs[i].profit);
                for (int j = i - 1; j >= 0; j--) {
                    if (jobs[j].end <= jobs[i].start) {
                        dp[i] = Math.max(dp[i], jobs[i].profit + dp[j]);
                        break;
                    }
                }
            }
            return dp[n - 1];
        }

        private class Job {
            int start;
            int end;
            int profit;

            Job(int startTime, int endTime, int profit) {
                this.start = startTime;
                this.end = endTime;
                this.profit = profit;
            }
        }
    }
}
