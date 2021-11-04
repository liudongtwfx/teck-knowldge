package algorithme.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class NextGreaterElementI {
    public static void main(String[] args) {

    }

    class Solution {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            Map<Integer, Integer> nextIndexMap = new HashMap<>();
            Deque<Integer> nums = new ArrayDeque<>();
            for (int i = nums2.length - 1; i >= 0; i--) {
                while (!nums.isEmpty()) {
                    Integer last = nums.getLast();
                    if (last < nums2[i]) {
                        nums.removeLast();
                        continue;
                    }
                    break;
                }
                nextIndexMap.put(nums2[i], nums.isEmpty() ? -1 : nums.getLast());
                nums.addLast(nums2[i]);
            }
            int[] ans = new int[nums1.length];
            for (int i = 0; i < nums1.length; i++) {
                ans[i] = nextIndexMap.get(nums1[i]);
            }
            return ans;
        }
    }
}
