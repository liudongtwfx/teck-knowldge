package algorithme.heap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author liudong17
 */
public class SmallestRangeCoveringElementsFromKLists632 {
    public static void main(String[] args) {
        new SmallestRangeCoveringElementsFromKLists632().bridge();
    }

    private void bridge() {
        String json = "[[4,10,12,24,26],[0,9,12,20],[5,18,22,30]]";
        Type type = new TypeToken<List<List<Integer>>>() {
        }.getType();
        List<List<Integer>> nums = new Gson().fromJson(json, type);
        int[] ints = new Solution().smallestRange(nums);
        System.out.println(new Gson().toJson(ints));
    }

    class Solution {
        public int[] smallestRange(List<List<Integer>> nums) {
            Queue<Point> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.currentValue));
            int max = 0;
            for (int i = 0; i < nums.size(); i++) {
                queue.add(new Point(nums.get(i).get(0), i));
                max = Math.max(max, nums.get(i).get(0));
            }
            int start = Integer.MIN_VALUE;
            int end = Integer.MAX_VALUE;
            int gap = Integer.MAX_VALUE;
            int[] idxArr = new int[nums.size()];
            while (queue.size() == nums.size()) {
                Point head = queue.poll();
                int currentGap = max - head.currentValue;
                if (currentGap < gap) {
                    start = head.currentValue;
                    end = max;
                    gap = currentGap;
                }
                if (idxArr[head.index] < nums.get(head.index).size() - 1) {
                    idxArr[head.index]++;
                    queue.add(new Point(nums.get(head.index).get(idxArr[head.index]), head.index));
                    max = Math.max(max, nums.get(head.index).get(idxArr[head.index]));
                }
            }
            return new int[]{start, end};
        }

        class Point {
            private final int currentValue;
            private final int index;

            public Point(int currentValue, int index) {
                this.currentValue = currentValue;
                this.index = index;
            }
        }
    }
}
