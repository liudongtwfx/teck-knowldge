package algorithme.dp;

import java.util.Arrays;

public class MinimumNumberOfWorkSessionsToFinishTheTasks1986 {
    private static final String input =
//            "[1,2,3] 3\n";
            //      "[1, 5, 7, 10, 3, 8, 4, 2, 6, 2] 10\n" +
            "[2,3,3,4,4,4,6,7,8,9,10] 15\n";

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(1807));
        new MinimumNumberOfWorkSessionsToFinishTheTasks1986().bridge();
    }

    private void bridge() {
        for (String s : input.split("\n")) {
            String[] split = s.split("]");
            Integer[] taskInteger = Arrays.stream(split[0].substring(1).split(","))
                    .map(String::trim)
                    .map(Integer::valueOf).toArray(Integer[]::new);
            int[] tasks = new int[taskInteger.length];
            for (int i = 0; i < taskInteger.length; i++) {
                tasks[i] = taskInteger[i];
            }
            System.out.println(new Solution().minSessions(tasks, Integer.parseInt(split[1].trim())));
        }
    }

    //1ms Accept
    class Solution {
        int res;
        int maxSessionTime;
        int[] tasks;
        int[] sessions;

        public int minSessions(int[] tasks, int sessionTime) {
            Arrays.sort(tasks);
            this.res = tasks.length;
            this.maxSessionTime = sessionTime;
            this.tasks = tasks;
            this.sessions = new int[tasks.length];
            dfs(tasks.length - 1, 0);
            return res;
        }

        private void dfs(int taskID, int sessionCount) {
            if (sessionCount > res) return; //prune, if we didn't use prune, it will be 2200ms, if lucky you get ac
            if (taskID == tasks.length) {
                res = Math.min(res, sessionCount);
                return;
            }
            for (int i = 0; i < sessionCount; i++)
                if (sessions[i] + tasks[taskID] <= maxSessionTime) { //put task into old session bucket
                    sessions[i] += tasks[taskID];
                    dfs(taskID - 1, sessionCount);
                    sessions[i] -= tasks[taskID];
                }
            sessions[sessionCount] += tasks[taskID]; //put task into new empty session bucket
            dfs(taskID - 1, sessionCount + 1);
            sessions[sessionCount] -= tasks[taskID];
        }
    }
}
