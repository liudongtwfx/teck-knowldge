package algorithme.sort;

import java.util.Arrays;
import java.util.Comparator;

public class MergeIntervals56 {
    public static void main(String[] args) {

    }

    class Solution {
        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
            int start = intervals[0][0];
            int end = intervals[0][1];
            int i = 0;
            int[][] ans = new int[intervals.length][2];
            for (int[] interval : intervals) {
                if (interval[0] <= end) {
                    end = Math.max(end, interval[1]);
                } else {
                    ans[i][0] = start;
                    ans[i++][1] = end;
                    start = interval[0];
                    end = interval[1];
                }
            }
            ans[i][0] = start;
            ans[i][1] = end;
            return Arrays.copyOfRange(ans, 0, i);
        }
    }
}
