package algorithme.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * 1381. Design a Stack With Increment Operation
 * https://leetcode.com/problems/design-a-stack-with-increment-operation/
 */
public class DesignAStackWithIncrementOperation {
    public static void main(String[] args) {

    }

    class CustomStack {
        private List<Integer> nums = new ArrayList<>();
        private int maxSize;

        public CustomStack(int maxSize) {
            this.maxSize = maxSize;
        }

        public void push(int x) {
            if (nums.size() < maxSize) {
                nums.add(x);
            }
        }

        public int pop() {
            if (nums.isEmpty()) {
                return -1;
            }
            int last = nums.get(nums.size() - 1);
            nums.remove(nums.size() - 1);
            return last;
        }

        public void increment(int k, int val) {
            for (int i = 0; i < k && i < nums.size(); i++) {
                nums.set(i, nums.get(i) + val);
            }
        }
    }
}
