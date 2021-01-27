package algorithme.search;


import java.util.Arrays;

public class SearchA2DMatrix {
    public static void main(String[] args) {
        new SearchA2DMatrix().bridge();
    }


    private void bridge() {
        String json = "[[1,3,5,7],[10,11,16,20],[23,30,34,60]]";
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int target = 3;
        System.out.println(new Solution().searchMatrix(matrix, target));
    }

    class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            return innerSearch(matrix, 0, matrix.length - 1, target);
        }

        private boolean innerSearch(int[][] matrix, int startLine, int endLine, int target) {

            if (startLine > endLine || startLine < 0 || endLine >= matrix.length) {
                return false;
            }

            int halfLine = (startLine + endLine) / 2;

            int index = Arrays.binarySearch(matrix[halfLine], target);
            if (index > -1) {
                return true;
            }

            if (startLine == endLine) {
                return false;
            }
            if (halfLine < matrix.length - 1 && matrix[halfLine + 1][0] <= target) {
                return innerSearch(matrix, halfLine + 1, endLine, target);
            }
            return innerSearch(matrix, startLine, endLine - 1, target);
        }
    }
}
