package algorithme.greedy;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class SingleThreadedCPU1834 {
    public static void main(String[] args) {
        new SingleThreadedCPU1834().bridge();
    }

    private void bridge() {
        String input = "[[1,2],[2,4],[3,2],[4,1]]";
        int[][] tasks = new Gson().fromJson(input, new TypeToken<int[][]>() {
        }.getType());

        System.out.println(Arrays.toString(new Solution().getOrder(tasks)));
    }

    class Solution {
        public int[] getOrder(int[][] tasks) {
            int[][] taskWithIndex = new int[tasks.length][3];
            for (int i = 0; i < tasks.length; i++) {
                taskWithIndex[i][0] = tasks[i][0];
                taskWithIndex[i][1] = tasks[i][1];
                taskWithIndex[i][2] = i;
            }

            Arrays.sort(taskWithIndex, (o1, o2) -> {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            });
            int currentTime = 0;
            int[] ans = new int[tasks.length];
            Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
                if (o1[1] != o2[1]) {
                    return o1[1] - o2[1];
                }
                return o1[2] - o2[2];
            });
            queue.offer(taskWithIndex[0]);
            int addIndex = 0;
            for (int i = 0; i < tasks.length; i++) {
                int[] first = queue.poll();
                currentTime = Math.max(currentTime, first[0]) + first[1];
                ans[i] = first[2];
                while (addIndex < tasks.length - 1 && taskWithIndex[addIndex + 1][0] <= currentTime) {
                    queue.offer(taskWithIndex[addIndex + 1]);
                    addIndex++;
                }
                if (queue.isEmpty() && addIndex < tasks.length - 1) {
                    queue.offer(taskWithIndex[++addIndex]);
                }
            }
            return ans;
        }
    }
}
