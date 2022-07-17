package algorithme.dp;

public class MinimumTimeToMakeRopeColorful1578 {
    public static void main(String[] args) {
        new MinimumTimeToMakeRopeColorful1578().bridge();
    }

    private void bridge() {
        String colors = "abaac";
        int[] neededTime = {1, 2, 3, 4, 5};
        System.out.println(new Solution().minCost(colors, neededTime));
    }

    class Solution {
        public int minCost(String colors, int[] neededTime) {
            char[] chars = colors.toCharArray();
            int sameStart = 0;
            int res = 0;
            for (int i = 1; i < chars.length; i++) {
                if (chars[i] != chars[i - 1]) {
                    res += computeMax(sameStart, i - 1, neededTime);
                    sameStart = i;
                }
            }
            res += computeMax(sameStart, chars.length - 1, neededTime);
            return res;
        }

        private int computeMax(int sameStart, int endStart, int[] neededTime) {
            int total = 0;
            int max = 0;
            for (int i = sameStart; i <= endStart; i++) {
                total += neededTime[i];
                max = Math.max(max, neededTime[i]);
            }
            return total - max;
        }
    }
}
