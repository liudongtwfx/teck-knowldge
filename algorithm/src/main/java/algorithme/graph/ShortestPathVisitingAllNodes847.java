package algorithme.graph;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.Queue;

public class ShortestPathVisitingAllNodes847 {
    public static void main(String[] args) {
        new ShortestPathVisitingAllNodes847().bridge();
    }

    private void bridge() {
        String json = "[[1,2,3,4,5,6,7,8,9],[0,2,3,4,5,6,7,8,9],[0,1,3,4,5,6,7,8,9],[0,1,2,4,5,6,7,8,9],[0,1,2,3,5,6,7,8,9],[0,1,2,3,4,6,7,8,9],[0,1,2,3,4,5,7,8,9],[0,1,2,3,4,5,6,8,9],[0,1,2,3,4,5,6,7,9,10],[0,1,2,3,4,5,6,7,8,11],[8],[9]]";
        Type type = new TypeToken<int[][]>() {
        }.getType();
        Gson gson = new Gson();
        int[][] graph = gson.fromJson(json, type);
        System.out.println(new Solution().shortestPathLength(graph));
    }

    class Solution {
        public int shortestPathLength(int[][] graph) {
            int size = graph.length;
            int target = (1 << size) - 1;
            boolean[][] visited = new boolean[size][target];
            Queue<int[]> queue = new ArrayDeque<>();
            for (int i = 0; i < size; ++i) {
                queue.offer(new int[]{i, 1 << i});
            }
            int step = 0;
            while (!queue.isEmpty()) {
                int n = queue.size();
                for (int i = 0; i < n; ++i) {
                    int[] cur = queue.poll();
                    int node = cur[0];
                    int state = cur[1];
                    if (state == target) {
                        return step;
                    }
                    if (visited[node][state]) {
                        continue;
                    }
                    visited[node][state] = true;
                    for (int next : graph[node]) {
                        queue.offer(new int[]{next, state | (1 << next)});
                    }
                }
                step++;
            }
            return -1;
        }
    }
}
