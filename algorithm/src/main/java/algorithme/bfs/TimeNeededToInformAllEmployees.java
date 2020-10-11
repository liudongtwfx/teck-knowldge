package algorithme.bfs;

import java.util.*;

/**
 * 1376. Time Needed to Inform All Employees
 * https://leetcode.com/problems/time-needed-to-inform-all-employees/
 */
public class TimeNeededToInformAllEmployees {
    public static void main(String[] args) {

    }

    class Solution {
        public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
            Map<Integer, List<Integer>> subordinates = new HashMap<>();
            for (int i = 0; i < n; i++) {
                if (manager[i] != -1) {
                    List<Integer> sub = subordinates.computeIfAbsent(manager[i], k -> new ArrayList<>());
                    sub.add(i);
                }
            }
            Queue<Node> q = new ArrayDeque<>();
            q.add(new Node(headID, 0));
            int ans = 0;
            while (!q.isEmpty()) {
                Node head = q.poll();
                for (Integer sub : subordinates.getOrDefault(head.current, Collections.emptyList())) {
                    int t = head.time + informTime[head.current];
                    ans = Math.max(t, ans);
                    q.add(new Node(sub, t));
                }
            }
            return ans;
        }
    }

    class Node {
        int current;
        int time;

        public Node(int current, int time) {
            this.current = current;
            this.time = time;
        }
    }
}
