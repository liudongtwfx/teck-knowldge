package algorithme.graph;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.*;

public class MinCostToConnectAllPoints1584 {
    public static void main(String[] args) {
        new MinCostToConnectAllPoints1584().bridge();
    }

    private void bridge() {
        String json = "[[0,0],[2,2],[3,10],[5,2],[7,0]]";
        int[][] points = new Gson().fromJson(json, new TypeToken<int[][]>() {
        }.getType());
        System.out.println(new Solution().minCostConnectPoints(points));
    }

    class Solution {
        public int minCostConnectPoints(int[][] points) {
            int ans = 0;
            Map<Integer, Queue<PointDis>> distanceMap = new HashMap<>();
            int[] minIndexes = new int[2];
            int minDis = Integer.MAX_VALUE;
            for (int i = 0; i < points.length; i++) {
                Queue<PointDis> distanceQueue = new PriorityQueue<>(Comparator.comparingInt(PointDis::getDistance));
                for (int j = 0; j < points.length; j++) {
                    if (i != j) {
                        int dis = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                        distanceQueue.add(new PointDis(j, dis));
                        if (dis < minDis) {
                            minIndexes[0] = i;
                            minIndexes[1] = j;
                            minDis = dis;
                        }
                    }
                }
                distanceMap.put(i, distanceQueue);
            }
            Set<Integer> vAdded = new LinkedHashSet<>();
            vAdded.add(minIndexes[0]);
            vAdded.add(minIndexes[1]);
            ans += minDis;
            while (vAdded.size() < points.length) {
                int minIndex = -1;
                minDis = Integer.MAX_VALUE;
                for (Integer v : vAdded) {
                    Queue<PointDis> pointDis = distanceMap.get(v);
                    PointDis tmp = null;
                    while (true) {
                        tmp = pointDis.peek();
                        if (tmp != null && vAdded.contains(tmp.desIndex)) {
                            pointDis.poll();
                            continue;
                        }
                        break;
                    }
                    if (tmp != null && minDis >= tmp.distance) {
                        minIndex = tmp.desIndex;
                        minDis = tmp.distance;
                    }
                }
                vAdded.add(minIndex);
                ans += minDis;
                System.out.println(ans);
            }
            vAdded.forEach(System.out::println);
            return ans;
        }

        class PointDis {
            int desIndex;
            int distance;

            public PointDis(int desIndex, int distance) {
                this.desIndex = desIndex;
                this.distance = distance;
            }

            public int getDesIndex() {
                return desIndex;
            }

            public int getDistance() {
                return distance;
            }
        }
    }
}
