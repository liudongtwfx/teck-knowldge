package algorithme.string;

public class MinimumDeletionCostToAvoidRepeatingLetters1578 {

    public static void main(String[] args) {
        new MinimumDeletionCostToAvoidRepeatingLetters1578().bridge();
    }

    private void bridge() {
        String s = "bbbaaa";
        int[] cost = {4, 9, 3, 8, 8, 9};
        int ans = new Solution().minCost(s, cost);
        System.out.println(ans);
    }

    class Solution {
        public int minCost(String s, int[] cost) {
            int count = 0;
            int index = 0;
            while (index < s.length()) {
                int innerStart = index;
                int max = Integer.MIN_VALUE;
                int totalCount = 0;
                while (innerStart < s.length() && s.charAt(innerStart) == s.charAt(index)) {
                    max = Math.max(max, cost[innerStart]);
                    totalCount += cost[innerStart];
                    ++innerStart;
                }
                if (innerStart - index > 1) {
                    count += (totalCount - max);
                }
                index = innerStart;
            }
            return count;
        }
    }
}
