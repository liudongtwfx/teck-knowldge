package algorithme.dp;

/**
 * 464. Can I Win
 * https://leetcode.com/problems/can-i-win/
 */
public class CanIWin {
    public static void main(String[] args) {
        new CanIWin().bridge();
    }

    private void bridge() {
        int maxChoosableInteger = 10;
        int desiredTotal = 11;
        System.out.println(new Solution().canIWin(maxChoosableInteger, desiredTotal));
        System.out.println(new Solution().canIWin(4, 6));
    }


    class Solution {
        public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
            if (maxChoosableInteger >= desiredTotal) {
                return true;
            }
            if ((1 + maxChoosableInteger) * maxChoosableInteger < desiredTotal) {
                return false;
            }
            boolean[] visited = new boolean[maxChoosableInteger + 1];
            return dfsCanWin(maxChoosableInteger, visited, desiredTotal, 0, true);
        }

        private boolean dfsCanWin(int maxChoosableInteger, boolean[] visited, int desiredTotal, int current, boolean first) {
            boolean secondCanWin = true;
            for (int i = maxChoosableInteger; i >= 1; i--) {
                if (!visited[i]) {
                    if (current + i >= desiredTotal) {
                        return true;
                    } else {
                        visited[i] = true;
                        boolean nextCanWin = dfsCanWin(maxChoosableInteger, visited, desiredTotal, current + i, !first);
                        if (nextCanWin && !first) {
                            secondCanWin = false;
                        }
                        if (first && !nextCanWin) {
                            return true;
                        }
                        visited[i] = false;
                    }
                }
            }
            if (first) {
                return false;
            }
            return secondCanWin;
        }
    }
}
