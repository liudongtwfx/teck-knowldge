package algorithme.tree;

import java.util.ArrayList;
import java.util.List;

public class RangeSumQueryMutable307 {
    public static void main(String[] args) {
        new RangeSumQueryMutable307().bridge();
    }

    private void bridge() {
        int[] nums = {9, -8};
        NumArray numArray = new NumArray(nums);
        numArray.update(0, 3);
        numArray.update(1, -3);
        System.out.println(numArray.sumRange(0, 1));
    }

    class NumArray {
        private final List<Integer> segmentList;
        private final int length;

        public NumArray(int[] nums) {
            int length = nums.length;
            this.length = length;
            int offset = getOffset(length);
            this.segmentList = new ArrayList<>(offset + length);
            for (int i = 0; i < offset; i++) {
                segmentList.add(0);
            }
            for (int i = 0; i < length; i++) {
                segmentList.add(nums[i]);
            }
            for (int i = offset - 1; i >= 0; i--) {
                int sum = (i * 2 + 2 < segmentList.size() ? segmentList.get(i * 2 + 2) : 0) + (i * 2 + 1 < segmentList.size() ? segmentList.get(i * 2 + 1) : 0);
                segmentList.set(i, sum);
            }
        }

        public void update(int index, int val) {
            int n = segmentList.size() - this.length + index;
            segmentList.set(n, val);
            while (n >= 0) {
                n = (n - 1) / 2;
                int sum = (n * 2 + 2 < segmentList.size() ? segmentList.get(n * 2 + 2) : 0) + (n * 2 + 1 < segmentList.size() ? segmentList.get(n * 2 + 1) : 0);
                segmentList.set(n, sum);
                if (n == 0) break;
            }
        }

        public int sumRange(int left, int right) {
            int offset = segmentList.size() - length;
            left += offset;
            right += offset;
            int sum = 0;
            while (left < right) {
                if ((left & 1) == 0) {
                    sum += segmentList.get(left);
                    left++;
                }

                if ((right & 1) == 1) {
                    sum += segmentList.get(right);
                    right--;
                }

                left = (left - 1) / 2;
                right = (right - 1) / 2;

            }
            if (left == right) {
                sum += segmentList.get(left);
            }
            return sum;
        }

        private int getOffset(int length) {
            int total = 1;
            while (total < length) {
                total *= 2;
            }
            return total - 1;
        }
    }
}