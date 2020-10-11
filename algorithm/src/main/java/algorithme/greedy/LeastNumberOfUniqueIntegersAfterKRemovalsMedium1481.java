package algorithme.greedy;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LeastNumberOfUniqueIntegersAfterKRemovalsMedium1481 {
    public static void main(String[] args) {

    }

    class Solution {
        public int findLeastNumOfUniqueInts(int[] arr, int k) {
            Map<Integer, Integer> numCountMap = new HashMap<>();
            for (Integer num : arr) {
                numCountMap.merge(num, 1, Integer::sum);
            }
            List<Map.Entry<Integer, Integer>> sortedEntry = numCountMap.entrySet().stream()
                    .sorted(Comparator.comparingInt(Map.Entry::getValue))
                    .collect(Collectors.toList());
            int total = numCountMap.size();
            for (Map.Entry<Integer, Integer> integerIntegerEntry : sortedEntry) {
                if (k <= 0) {
                    return total;
                }
                if (k >= integerIntegerEntry.getValue()) {
                    total -= 1;
                }
                k -= integerIntegerEntry.getValue();
            }
            return total;
        }
    }
}
