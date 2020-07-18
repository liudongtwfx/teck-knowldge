package algorithme.graph;

import java.util.*;

public class PathWithMaximumProbability1514 {
    public static void main(String[] args) {
        new PathWithMaximumProbability1514().bridge();
    }

    private void bridge() {
        int[][] edges = {{0, 1}, {1, 2}, {1, 3}, {2, 3}, {0, 3}, {3, 4}};
        double[] succProb = {0.9, 0.9, 0.9, 0.9, 0.5, 0.5};
        int start = 0, end = 4, n = 5;
        System.out.println(new Solution().maxProbability(n, edges, succProb, start, end));
    }

    static class Solution {
        public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
            double[] probs = new double[n];
            probs[start] = 1;
            Map<Integer, List<StartEndProbability>> edgeMap = new HashMap<>();
            for (int i = 0; i < succProb.length; i++) {
                StartEndProbability p = new StartEndProbability(edges[i][0], edges[i][1], succProb[i]);
                edgeMap.merge(p.start, new ArrayList<>(Collections.singletonList(p)), (a, b) -> {
                    a.addAll(b);
                    return a;
                });
                StartEndProbability another = new StartEndProbability(edges[i][1], edges[i][0], succProb[i]);
                edgeMap.merge(another.start, new ArrayList<>(Collections.singletonList(another)), (a, b) -> {
                    a.addAll(b);
                    return a;
                });
            }
            Set<Integer> visited = new HashSet<>();
            Queue<Integer> queue = new LinkedList<>();
            queue.add(start);
            visited.add(start);
            while (!queue.isEmpty()) {
                Integer current = queue.poll();
                List<StartEndProbability> startEndProbabilities = edgeMap.getOrDefault(current, Collections.emptyList());
                for (StartEndProbability s : startEndProbabilities) {
                    if (probs[s.end] < probs[s.start] * s.probability) {
                        queue.add(s.end);
                        probs[s.end] = probs[s.start] * s.probability;
                    }
                }
            }
            return probs[end];
        }

        class StartEndProbability {
            int start;
            int end;
            double probability;

            public StartEndProbability(int start, int end, double probability) {
                this.start = start;
                this.end = end;
                this.probability = probability;
            }
        }
    }
}
