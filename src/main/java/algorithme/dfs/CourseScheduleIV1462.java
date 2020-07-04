package algorithme.dfs;

import java.util.*;

public class CourseScheduleIV1462 {
    public static void main(String[] args) {
        new CourseScheduleIV1462().bridge();
    }

    private void bridge() {
        int n = 5;
        int[][] prerequisites = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        int[][] queries = {{0, 4}, {4, 0}, {1, 3}, {3, 0}};
        List<Boolean> ans = new Solution().checkIfPrerequisite(n, prerequisites, queries);
        for (Boolean an : ans) {
            System.out.println(an);
        }
    }

    class Solution {
        public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
            Map<Integer, Set<Integer>> prevMap = new HashMap<>();
            boolean[] visited = new boolean[prerequisites.length];
            for (int i = 0; i < prerequisites.length; i++) {
                dfs(prerequisites, prevMap, visited, prerequisites[i][1]);
            }
            List<Boolean> ans = new ArrayList<>();
            for (int[] query : queries) {
                ans.add(prevMap.getOrDefault(query[1], Collections.emptySet()).contains(query[0]));
            }
            return ans;
        }

        private void dfs(int[][] prerequisites, Map<Integer, Set<Integer>> prevMap, boolean[] visited, int target) {
            for (int i = 0; i < prerequisites.length; i++) {
                if (visited[i] || prerequisites[i][1] != target) {
                    continue;
                }
                visited[i] = true;
                Set<Integer> ans = prevMap.getOrDefault(target, new HashSet<>());
                ans.add(prerequisites[i][0]);
                dfs(prerequisites, prevMap, visited, prerequisites[i][0]);
                ans.addAll(prevMap.getOrDefault(prerequisites[i][0], Collections.emptySet()));
                prevMap.put(target, ans);
            }
        }
    }
}
