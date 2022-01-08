package algorithme;

public class MaximalRectangle {
    public static void main(String[] args) {

    }

    class Solution {
        public int maximalRectangle(char[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            int[] tmp = new int[n];
            int max = 0;
            for (int i = 0; i < m; i++) {
                int tmpMin = Integer.MAX_VALUE;
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == '0') {
                        tmp[j] = 0;
                        continue;
                    }
                    tmp[j]++;
                    max = Math.max(getCurrentMax(tmp, j), max);
                }
            }
            return max;
        }

        private int getCurrentMax(int[] tmp, int endPos) {
            int max = 0, minValue = Integer.MAX_VALUE;
            for (int i = endPos; i >= 0; i--) {
                if (tmp[i] == 0) {
                    return max;
                }
                minValue = Math.min(minValue, tmp[i]);
                max = Math.max(max, minValue * (endPos - i + 1));
            }
            return max;
        }
    }
}
