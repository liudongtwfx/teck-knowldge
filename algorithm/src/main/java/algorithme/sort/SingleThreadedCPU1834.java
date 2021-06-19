package algorithme.sort;

import com.google.gson.Gson;

import java.util.*;

public class SingleThreadedCPU1834 {
    public static void main(String[] args) {
        new SingleThreadedCPU1834().bridge();
    }

    private void bridge() {
        String json = "[[5,2],[7,2],[9,4],[6,3],[5,10],[1,1]]";
        Gson gson = new Gson();
        int[][] tasks = gson.fromJson(json, int[][].class);
        int[] order = new Solution().getOrder(tasks);
        System.out.println(Arrays.toString(order));
    }

    class Solution {
        public int[] getOrder(int[][] tasks) {
            int length = tasks.length;
            List<IndexWrapper> indexWrapperList = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                indexWrapperList.add(new IndexWrapper(i, tasks[i][0], tasks[i][1]));
            }
            indexWrapperList.sort(new IndexWrapperCmp());
            int index = 0;
            int[] ans = new int[length];
            int currentTime = indexWrapperList.get(0).enqueueTime;
            Queue<IndexWrapper> indexWrappers = new PriorityQueue<>((o1, o2) -> {
                if (o1.processingTime != o2.processingTime) {
                    return o1.processingTime - o2.processingTime;
                }
                return o1.index - o2.index;
            });
            int indexWrappersIndex = 1;
            indexWrappers.offer(indexWrapperList.get(0));
            while (!indexWrappers.isEmpty()) {
                IndexWrapper poll = indexWrappers.poll();
                ans[index++] = poll.index;
                currentTime = Math.max(currentTime, poll.enqueueTime) + poll.processingTime;
                IndexWrapper in;
                while (indexWrappersIndex < length && (in = indexWrapperList.get(indexWrappersIndex)).enqueueTime <= currentTime) {
                    indexWrappers.offer(in);
                    indexWrappersIndex++;
                }
                if (indexWrappers.isEmpty() && indexWrappersIndex < length) {
                    indexWrappers.offer(indexWrapperList.get(indexWrappersIndex++));
                }
            }
            return ans;
        }

        class IndexWrapper {
            int index;
            int enqueueTime;
            int processingTime;

            public IndexWrapper(int index, int enqueueTime, int processingTime) {
                this.index = index;
                this.enqueueTime = enqueueTime;
                this.processingTime = processingTime;
            }
        }

        class IndexWrapperCmp implements Comparator<IndexWrapper> {
            @Override
            public int compare(IndexWrapper o1, IndexWrapper o2) {
                if (o1.enqueueTime == o2.enqueueTime) {
                    return o1.processingTime - o2.processingTime;
                }
                return o1.enqueueTime - o2.enqueueTime;
            }
        }
    }
}
