package algorithme.bfs;

import java.util.*;

public class ShortestPathWithAlternatingColors1129 {
    public static void main(String[] args) {

    }

    class Solution {
        public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
            HashMap<Integer, Integer>[] edgeMap = new HashMap[n];
            for (int i = 0; i < n; i++) {
                edgeMap[i] = new HashMap<>();
            }
            addToMap(redEdges, edgeMap, true);
            addToMap(blueEdges, edgeMap, false);
            return nearest(n, edgeMap);
        }

        private int[] nearest(int n, Map<Integer, Integer>[] edgeMap) {
            int[] ans = new int[n];
            Arrays.fill(ans, -1);
            ans[0] = 0;
            List<int[]> edgeList = new ArrayList<>();
            boolean[][][] visited = new boolean[n][n][2];
            for (Map.Entry<Integer, Integer> entry : edgeMap[0].entrySet()) {
                if (ans[entry.getKey()] == -1) {
                    ans[entry.getKey()] = 1;
                }
                if (entry.getValue() == 2) {
                    edgeList.add(new int[]{0, entry.getKey(), 0});
                    edgeList.add(new int[]{0, entry.getKey(), 1});
                    visited[0][entry.getKey()][0] = true;
                    visited[0][entry.getKey()][1] = true;
                } else {
                    edgeList.add(new int[]{0, entry.getKey(), entry.getValue()});
                    visited[0][entry.getKey()][entry.getValue()] = true;
                }
            }
            int step = 1;
            while (!edgeList.isEmpty()) {
                List<int[]> next = new ArrayList<>();
                for (int[] edge : edgeList) {
                    if (ans[edge[1]] == -1) {
                        ans[edge[1]] = step;
                    }
                    Map<Integer, Integer> nextEdge = edgeMap[edge[1]];
                    for (Map.Entry<Integer, Integer> entry : nextEdge.entrySet()) {
                        if (!visited[edge[1]][entry.getKey()][1 - edge[2]] &&
                                (entry.getValue() == 2 || entry.getValue() != edge[2])) {
                            next.add(new int[]{edge[1], entry.getKey(), 1 - edge[2]});
                            visited[edge[1]][entry.getKey()][1 - edge[2]] = true;
                        }
                    }
                }
                edgeList = next;
                step++;
            }
            return ans;
        }

        private void addToMap(int[][] redEdges, HashMap<Integer, Integer>[] edgeMap, boolean isRed) {
            for (int[] redEdge : redEdges) {
                int left = redEdge[0];
                Integer right = redEdge[1];
                if (edgeMap[left].containsKey(right)) {
                    edgeMap[left].put(right, 2);
                } else {
                    edgeMap[left].put(right, isRed ? 1 : 0);
                }
            }
        }
    }
}
