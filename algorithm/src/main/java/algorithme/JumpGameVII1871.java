package algorithme;

import java.util.ArrayList;
import java.util.List;

public class JumpGameVII1871 {
    public static void main(String[] args) {
        new JumpGameVII1871().bridge();
    }

    private void bridge() {
        String s = "011010";
        boolean canReach = new Solution().canReach(s, 2, 3);
        System.out.println(canReach);
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
}
