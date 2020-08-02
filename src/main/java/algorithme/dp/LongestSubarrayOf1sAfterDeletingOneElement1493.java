package algorithme.dp;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

public class LongestSubarrayOf1sAfterDeletingOneElement1493 {
    public static void main(String[] args) {
        List<? extends Serializable> serializables = Lists.newArrayList(1, "2", 1.2);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println(Thread.currentThread().getName() + " shut down")));
    }


    class Solution {
        public int longestSubarray(int[] nums) {
            int len = nums.length;
            int[] rightMaxOnes = new int[len];
            int currentOnes = 0;
            for (int i = len - 1; i >= 0; i--) {
                rightMaxOnes[i] = currentOnes;
                if (nums[i] == 0) {
                    currentOnes = 0;
                } else {
                    currentOnes++;
                }
            }
            int ans = 0;
            currentOnes = 0;
            for (int i = 0; i < len; i++) {
                ans = Math.max(ans, currentOnes + rightMaxOnes[i]);
                if (nums[i] == 0) {
                    currentOnes = 0;
                } else {
                    currentOnes++;
                }
            }
            return ans;
        }
    }
}

