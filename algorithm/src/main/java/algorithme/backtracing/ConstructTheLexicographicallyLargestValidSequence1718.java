package algorithme.backtracing;

import java.util.Arrays;

public class ConstructTheLexicographicallyLargestValidSequence1718 {
    public static void main(String[] args) {
        new ConstructTheLexicographicallyLargestValidSequence1718().bridge();
    }

    private void bridge() {
        int[] ans = new Solution().constructDistancedSequence(50);
        System.out.println(Arrays.toString(ans));
    }

    class Solution {
        private int count;

        public int[] constructDistancedSequence(int n) {
            int[] ans = new int[2 * n - 1];
            boolean[] used = new boolean[n + 1];
            backtrace(ans, 0, used, n);
            System.out.println(count);
            return ans;
        }

        private boolean backtrace(int[] ans, int i, boolean[] used, int zeroCount) {
            count++;
            if (zeroCount == 0) {
                return true;
            }
            if (ans[i] != 0) {
                return backtrace(ans, i + 1, used, zeroCount);
            }
            for (int k = used.length - 1; k > 0; k--) {
                if (used[k]) {
                    continue;
                }
                int m = (k == 1 ? i : i + k);
                if (m < ans.length && ans[m] == 0) {
                    used[k] = true;
                    ans[i] = k;
                    ans[m] = k;
                    zeroCount--;
                    if (zeroCount == 0) {
                        return true;
                    }
                    if (backtrace(ans, i + 1, used, zeroCount)) {
                        return true;
                    }
                    used[k] = false;
                    ans[i] = 0;
                    ans[m] = 0;
                    zeroCount++;
                }
            }
            return false;
        }
    }

    class SolutionBest {
        private int count;

        public int[] constructDistancedSequence(int n) {
            int[] res = new int[n * 2 - 1];
            boolean[] vis = new boolean[n + 1];
            dfs(res, vis, 0, n);
            System.out.println(count);
            return res;
        }

        boolean dfs(int[] res, boolean[] vis, int idx, int n) {
            count++;
            if (idx == res.length) return true;
            if (res[idx] != 0) return dfs(res, vis, idx + 1, n);
            for (int i = n; i > 0; i--) {
                if (vis[i]) continue;
                vis[i] = true;
                res[idx] = i;
                if (i == 1) {
                    if (dfs(res, vis, idx + 1, n)) return true;
                } else {
                    if (idx + i < res.length && res[idx + i] == 0) {
                        res[idx + i] = i;
                        if (dfs(res, vis, idx + 1, n)) return true;
                        res[idx + i] = 0;
                    }
                }
                vis[i] = false;
                res[idx] = 0;
            }
            return false;
        }
    }
}
