package algorithme.dfs;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Stack;

import static java.lang.Math.max;

public class LongestIncreasingPathInaMatrix {
    public static void main(String[] args) {
        new LongestIncreasingPathInaMatrix().bridge();
    }

    private void bridge() {
        String input = "[[0,1,2,3,4,5,6,7,8,9],[19,18,17,16,15,14,13,12,11,10],[20,21,22,23,24,25,26,27,28,29],[39,38,37,36,35,34,33,32,31,30],[40,41,42,43,44,45,46,47,48,49],[59,58,57,56,55,54,53,52,51,50],[60,61,62,63,64,65,66,67,68,69],[79,78,77,76,75,74,73,72,71,70],[80,81,82,83,84,85,86,87,88,89],[99,98,97,96,95,94,93,92,91,90],[100,101,102,103,104,105,106,107,108,109],[119,118,117,116,115,114,113,112,111,110],[120,121,122,123,124,125,126,127,128,129],[139,138,137,136,135,134,133,132,131,130],[0,0,0,0,0,0,0,0,0,0]]";
        int[][] matrix = new Gson().fromJson(input, new TypeToken<int[][]>() {
        }.getType());
        Arrays.stream(matrix).forEach(line -> System.out.println(Arrays.toString(line)));
        System.out.println(new Solution().longestIncreasingPath(matrix));
    }

    class Solution {
        private final int[][] DIRECTION = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        private int[][] dp;

        public int longestIncreasingPath(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            dp = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j] = -1;
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (dp[i][j] == -1) {
                        dfs(matrix, i, j, -1);
                    }
                }
            }
            int ans = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(dp[i][j] + " ");
                    ans = max(ans, dp[i][j]);
                }
                System.out.println();
            }
            return ans;
        }

        private int dfs(int[][] matrix, int i, int j, int last) {
            if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] <= last) {
                return 0;
            }
            if (dp[i][j] != -1) return dp[i][j];
            int max = 0;
            for (int[] d : DIRECTION) {
                max = max(max, dfs(matrix, i + d[0], j + d[1], matrix[i][j]) + 1);
            }
            dp[i][j] = max(dp[i][j], max);
            return max;
        }
    }

    class Solution1 {
        public int longestValidParentheses(String s) {
            int _max = 0, last = -1;
            Stack<Integer> left = new Stack<>();
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == '(') left.push(i);
                else {
                    if (left.empty()) last = i;
                    else {
                        left.pop();
                        if (left.empty()) {
                            _max = max(_max, i - last);
                        } else {
                            _max = max(_max, i - left.peek());
                        }
                    }
                }
            }
            return _max;
        }
    }
}
