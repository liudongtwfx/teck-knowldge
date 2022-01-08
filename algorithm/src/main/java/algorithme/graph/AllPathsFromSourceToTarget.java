package algorithme.graph;

import java.util.ArrayList;
import java.util.List;

public class AllPathsFromSourceToTarget {
    public static void main(String[] args) {

    }

    class Solution {
        private List<List<Integer>> ans = new ArrayList<>();

        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            dfs(graph, 0, new boolean[graph.length], new ArrayList<>());
            return ans;
        }

        private void dfs(int[][] graph, int index, boolean[] visited, List<Integer> path) {
            if (index >= graph.length || visited[index]) {
                return;
            }
            path.add(index);
            if (index == graph.length - 1) {
                ans.add(new ArrayList<>(path));
                return;
            }
            visited[index] = true;
            for (int i = 0; i < graph[index].length; i++) {
                dfs(graph, graph[index][i], visited, path);
            }
            visited[index] = false;
            path.remove(path.size() - 1);
        }
    }
}
