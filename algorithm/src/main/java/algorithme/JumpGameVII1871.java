package algorithme;

import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.List;

public class JumpGameVII1871 {
    public static void main(String[] args) {
        new JumpGameVII1871().bridge();
    }

    private void bridge() {
//        String s = "011010";
//        boolean canReach = new Solution().canReach(s, 2, 3);
//        System.out.println(canReach);
        int ans = new Solution1().removeDuplicates(new int[]{1, 1, 1, 2, 2, 2, 2, 3, 3, 5});
        System.out.println(ans);
        System.out.println("hello world");
    }

    class Solution {
        public boolean canReach(String s, int minJump, int maxJump) {
            List<Integer> canJumped = new ArrayList<>();
            canJumped.add(0);
            for (int i = 1; i < s.length(); i++) {
                char c = s.charAt(i);
                System.out.println("hello world");
                if (c == '0') {
                    for (int j = canJumped.size() - 1; j >= 0; j--) {
                        if (canJumped.get(j) >= i - maxJump && canJumped.get(j) <= i - minJump) {
                            canJumped.add(i);
                            break;
                        }
                    }
                }
            }
            return canJumped.get(canJumped.size() - 1) == s.length() - 1;
        }
    }

    class Solution1 {
        public int removeDuplicates(int[] nums) {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            int i = 0;
            for (int n : nums) {
                if (i < 2 || n > nums[i - 2]) {
                    nums[i++] = n;
                }
            }
            stopWatch.stop();
            System.out.println(stopWatch.formatTime());
            return i;
        }
    }
}
