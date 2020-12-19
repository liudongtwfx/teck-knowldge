package algorithme.heap;

import java.util.*;

/**
 * @author liudong17
 */
public class MinimizeDeviationInArray1675 {
    public static void main(String[] args) {
        new MinimizeDeviationInArray1675().bridge();
    }

    private void bridge() {
        int[] nums = {10, 4, 3};
        System.out.println(new Solution().minimumDeviation(nums));
    }

    class Solution {
        public int minimumDeviation(int[] nums) {
            List<List<Integer>> numsList = new ArrayList<>();
            for (int num : nums) {
                if ((num & 1) == 1) {
                    numsList.add(Arrays.asList(num, num * 2));
                    continue;
                }
                List<Integer> list = new ArrayList<>();
                while ((num & 1) == 0) {
                    list.add(num);
                    num >>= 1;
                }
                list.add(num);
                Collections.reverse(list);
                numsList.add(list);
            }
            int[] ints = smallestRange(numsList);
            return ints[1] - ints[0];
        }

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
