package algorithme.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class RemoveCoveredIntervals1288 {
    public static void main(String[] args) {

    }

    class Solution {
        public int removeCoveredIntervals(int[][] intervals) {
            Arrays.sort(intervals, (o1, o2) -> {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                }
                return o2[1] - o1[1];
            });
            int len = intervals.length;
            Set<Integer> removed = new HashSet<>();
            for (int i = 0; i < len; i++) {
                if (removed.contains(i)) {
                    continue;
                }
                for (int j = i; j < len; j++) {
                    if (intervals[j][1] < intervals[i][1]) {
                        removed.add(j);
                    }
                }
            }
            return len - removed.size();
        }
    }
}
