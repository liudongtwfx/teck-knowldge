package algorithme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupThePeopleGivenTheGroupSizeTheyBelongTo {
    public static void main(String[] args) {

    }

    class Solution {
        public List<List<Integer>> groupThePeople(int[] groupSizes) {
            Map<Integer, List<Integer>> countMap = new HashMap<>();

            for (int i = 0; i < groupSizes.length; i++) {
                List<Integer> sizeGroups = countMap.getOrDefault(groupSizes[i], new ArrayList<>());
                sizeGroups.add(i);
                countMap.put(groupSizes[i], sizeGroups);
            }

            List<List<Integer>> res = new ArrayList<>();

            for (Map.Entry<Integer, List<Integer>> entry : countMap.entrySet()) {
                Integer groupSize = entry.getKey();
                int count = entry.getValue().size() / groupSize;
                for (int i = 0; i < count; i++) {
                    res.add(entry.getValue().subList(i * groupSize, (i + 1) * groupSize));
                }
            }
            return res;
        }
    }
}
