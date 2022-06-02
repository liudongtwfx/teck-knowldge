package algorithme.graph;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IsGraphBipartite785 {
    public static void main(String[] args) {
        String input = "[[1,3],[0,2],[1,3],[0,2]]";
        int[][] graph = new Gson().fromJson(input, new TypeToken<int[][]>() {
        }.getType());
        Solution solution = new Solution();
        System.out.println(solution.isBipartite(graph));
        // solution.count.forEach((k, v) -> System.out.println(k + ":" + v));
    }


    static class Solution {
        private Set<String> count = new HashSet<>();

        public boolean isBipartite(int[][] graph) {
            int n = graph.length;
            int[] group = new int[n];
            group[0] = 1;
            for (int i = 0; i < n; i++) {
                if (group[i] == 0) {
                    group[i] = 1;
                }
                if (!dfs(graph, group, i, new boolean[n], group[i])) {
                    return false;
                }
            }
            return true;
        }

        private boolean dfs(int[][] graph, int[] group, int start, boolean[] visited, int expectGroup) {
            if (visited[start]) {
                return true;
            }
            if (group[start] != 0 && group[start] != expectGroup) {
                return false;
            }
            visited[start] = true;
            group[start] = expectGroup;
            for (int i = 0; i < graph[start].length; i++) {
                String key = Stream.of(start, graph[start][i]).map(String::valueOf).collect(Collectors.joining("_"));
                if (count.add(key) && !dfs(graph, group, graph[start][i], visited, 3 - expectGroup)) {
                    return false;
                }
            }
            return true;
        }
    }
}
