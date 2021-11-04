package algorithme.dfs;

import java.util.*;
import java.util.stream.Collectors;

public class PartitionArrayIntoTwoArraysToMinimizeSumDifference {
    public static void main(String[] args) {
        new PartitionArrayIntoTwoArraysToMinimizeSumDifference().bridge();
    }

    private void bridge() {
        int[] nums = {-7016943, 0, 2205243, -794066, -6795006, 5322408, 9699476, 6544247, 6992622, 7272161, 5469637, 4806999, -8562708, -5242263, 9037593, -2746735, 8072395, -1386148, 4745179, 3801334, -4086041, 0, 555427, -9249908, 5045948, -7943170, 1665466, 9514306, 7960606, -142676};
        System.out.println(nums.length);
        System.out.println(new Solution().minimumDifference(nums));
    }

    class Solution {
        public int minimumDifference(int[] nums) {
            long start = System.currentTimeMillis();
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            Map<Integer, Set<Integer>> leftParts = getMap(nums, 0, nums.length / 2);
            Map<Integer, Set<Integer>> rightParts = getMap(nums, nums.length / 2, nums.length);
            int ans = Integer.MAX_VALUE;
            int compareCount = 0;
            for (int i = 0; i <= nums.length / 2; i++) {
                List<Integer> left = leftParts.get(i).stream().sorted().collect(Collectors.toList());
                List<Integer> right = rightParts.get(nums.length / 2 - i).stream().sorted().collect(Collectors.toList());
                for (Integer integer : left) {
                    for (Integer integer1 : right) {
                        ans = Math.min(ans, Math.abs(sum - (integer1 + integer) * 2));
                        compareCount++;
                    }
                }
            }
            System.out.println(System.currentTimeMillis() - start);
            System.out.println(compareCount);
            return ans;
        }

        private Map<Integer, Set<Integer>> getMap(int[] nums, int start, int end) {
            Map<Integer, Set<Integer>> partMap = new HashMap<>();
            int total = 0;
            for (int i = start; i < end; i++) {
                final int j = i;
                total += nums[i];
                Map<Integer, Set<Integer>> newParts = new HashMap<>(partMap);
                Set<Integer> forAddV = new HashSet<>();
                forAddV.add(nums[i]);
                newParts.merge(1, forAddV, (s, v) -> {
                    Set<Integer> newSet = new HashSet<>(s);
                    newSet.addAll(v);
                    return newSet;
                });
                partMap.forEach((k, v) -> {
                    Set<Integer> newSums = v.stream().map(n -> n + nums[j]).collect(Collectors.toSet());
                    newParts.merge(k + 1, newSums, (s1, s2) -> {
                        Set<Integer> newSet = new HashSet<>(s1);
                        newSet.addAll(s2);
                        return newSet;
                    });
                });
                partMap = newParts;
            }
            partMap.put(0, Collections.singleton(0));
            partMap.put(nums.length / 2, Collections.singleton(total));
            return partMap;
        }
    }
}
