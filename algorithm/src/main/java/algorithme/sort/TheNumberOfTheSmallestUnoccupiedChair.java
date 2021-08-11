package algorithme.sort;

import java.util.*;
import java.util.stream.Collectors;

public class TheNumberOfTheSmallestUnoccupiedChair {
    public static void main(String[] args) {
        new TheNumberOfTheSmallestUnoccupiedChair().bridge();
    }

    private void bridge() {
        int[][] time = {{3, 10}, {1, 5}, {2, 6}};
        int targetFriend = 0;
        System.out.println(new Solution().smallestChair(time, targetFriend));
    }

    class Solution {
        public int smallestChair(int[][] times, int targetFriend) {
            int n = times.length;
            Map<Integer, List<TimeLane>> timeLaneMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                TimeLane timeLane = new TimeLane(i, times[i][0], times[i][1]);
                List<TimeLane> timeLanes = timeLaneMap.getOrDefault(times[i][0], new ArrayList<>());
                timeLanes.add(timeLane);
                timeLaneMap.put(times[i][0], timeLanes);
                List<TimeLane> timeLanes1 = timeLaneMap.getOrDefault(times[i][1], new ArrayList<>());
                timeLanes1.add(timeLane);
                timeLaneMap.put(times[i][1], timeLanes1);
            }
            Queue<Integer> tmp = new PriorityQueue<>();
            for (int i = 0; i < n; i++) {
                tmp.add(i);
            }
            for (Integer timeLane : timeLaneMap.keySet().stream().sorted().collect(Collectors.toList())) {
                List<TimeLane> timeLanes = timeLaneMap.get(timeLane);
                for (TimeLane lane : timeLanes) {
                    if (lane.leaveTime == timeLane) {
                        tmp.add(lane.seatIndex);
                    }
                }
                for (TimeLane lane : timeLanes) {
                    if (lane.arriveTime == timeLane) {
                        int i = tmp.poll();
                        if (lane.index == targetFriend) {
                            return i;
                        }
                        lane.seatIndex = i;
                    }
                }
            }
            return 0;
        }

        class TimeLane {
            int index;
            int arriveTime;
            int leaveTime;
            int seatIndex;

            public TimeLane(int index, int arriveTime, int leaveTime) {
                this.index = index;
                this.arriveTime = arriveTime;
                this.leaveTime = leaveTime;
            }
        }
    }
}
