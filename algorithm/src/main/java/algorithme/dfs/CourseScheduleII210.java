package algorithme.dfs;

import java.util.*;

public class CourseScheduleII210 {

    public static void main(String[] args) {
        new CourseScheduleII210().bridge();
        List<Integer> nums = new ArrayList<>();
    }

    private void bridge() {
        int[] ans = new Solution().findOrder(3, new int[][]{{1, 0}, {0, 2}, {2, 1}});
        System.out.println(Arrays.toString(ans));
    }

    class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            int[] ans = new int[numCourses];
            Set<Integer> added = new HashSet<>();
            Map<Integer, List<Integer>> prerequisiteMap = new HashMap<>();
            for (int[] prerequisite : prerequisites) {
                if (prerequisiteMap.getOrDefault(prerequisite[1], Collections.emptyList()).contains(prerequisite[0])) {
                    return new int[]{};
                }
                prerequisiteMap.putIfAbsent(prerequisite[0], new ArrayList<>());
                prerequisiteMap.computeIfPresent(prerequisite[0], (k, originV) -> {
                    originV.add(prerequisite[1]);
                    return originV;
                });
            }
            int index = 0;
            while (added.size() < numCourses) {
                int add = 0;
                for (int i = 0; i < numCourses; i++) {
                    if (!added.contains(i) && hasNoPre(prerequisiteMap.get(i), added)) {
                        added.add(i);
                        ans[index++] = i;
                        add++;
                    }
                }
                if (add == 0) {
                    return new int[]{};
                }
            }
            return ans;
        }

        private boolean hasNoPre(List<Integer> integers, Set<Integer> added) {
            if (integers == null || integers.isEmpty()) {
                return true;
            }
            for (Integer integer : integers) {
                if (!added.contains(integer)) {
                    return false;
                }
            }
            return true;
        }
    }
}
