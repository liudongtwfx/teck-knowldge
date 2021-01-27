package algorithme;


/**
 * You are given two arrays rowSum and colSum of non-negative integers where rowSum[i] is the sum of the elements in the ith row and colSum[j] is the sum of the elements of the jth column of a 2D matrix. In other words, you do not know the elements of the matrix, but you do know the sums of each row and column.
 * <p>
 * Find any matrix of non-negative integers of size rowSum.length x colSum.length that satisfies the rowSum and colSum requirements.
 * <p>
 * Return a 2D array representing any matrix that fulfills the requirements. It's guaranteed that at least one matrix that fulfills the requirements exists.
 */
public class FindValidMatrixGivenRowAndColumnSums {
    public static void main(String[] args) {

    }

    /**
     * [3,8], colSum = [4,7]
     * 1 2
     * 3 5
     */

    class Solution {
        public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
            int m = rowSum.length, n = colSum.length;
            int[][] res = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    res[i][j] = Math.min(rowSum[i], colSum[j]);
                    rowSum[i] -= res[i][j];
                    colSum[j] -= res[i][j];
                }
            }
            return res;
        }
    }
}
