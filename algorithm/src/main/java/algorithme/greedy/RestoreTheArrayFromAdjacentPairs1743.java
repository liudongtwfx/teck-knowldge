package algorithme.greedy;

import java.util.*;

public class RestoreTheArrayFromAdjacentPairs1743 {
    public static void main(String[] args) {
        new RestoreTheArrayFromAdjacentPairs1743().bridge();
    }


    private void bridge() {
        int[][] param = {{4, -2}, {1, 4}, {-3, 1}};
        System.out.println(Arrays.toString(new Solution().restoreArray(param)));
    }

    class Solution {
        public int[] restoreArray(int[][] adjacentPairs) {
            Map<Integer, List<Integer>> adjacentMap = new HashMap<>();
            Set<Integer> nums = new HashSet<>();
            for (int[] adjacentPair : adjacentPairs) {
                int key = Math.min(adjacentPair[0], adjacentPair[1]);
                int value = Math.max(adjacentPair[0], adjacentPair[1]);
                List<Integer> orDefault = adjacentMap.getOrDefault(key, new ArrayList<>());
                orDefault.add(value);
                adjacentMap.put(key, orDefault);

                orDefault = adjacentMap.getOrDefault(value, new ArrayList<>());
                orDefault.add(key);
                adjacentMap.put(value, orDefault);
                for (int i : adjacentPair) {
                    if (nums.contains(i)) {
                        nums.remove(i);
                    } else {
                        nums.add(i);
                    }
                }
            }
            int[] ans = new int[adjacentPairs.length + 1];
            int min = Integer.MAX_VALUE;

            for (Integer num : nums) {
                min = Math.min(min, num);
            }
            Set<Integer> visitd = new HashSet<>();
            for (int i = 0; i < ans.length - 1; i++) {
                ans[i] = min;
                visitd.add(min);
                for (Integer integer : adjacentMap.get(min)) {
                    if (!visitd.contains(integer)) {
                        min = integer;
                        break;
                    }
                }
            }
            ans[ans.length - 1] = min;
            return ans;
        }
    }
}
