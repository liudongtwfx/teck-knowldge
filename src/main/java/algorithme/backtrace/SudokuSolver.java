package algorithme.backtrace;

/**
 * 36. Valid Sudoku
 * https://leetcode.com/problems/valid-sudoku/
 *
 * @author liudong
 */
public class SudokuSolver {
    public static void main(String[] args) {
        new SudokuSolver().bridge();
    }


    private void bridge() {
        String[][] boardString = {
                {"5", "3", ".", ".", "7", ".", ".", ".", "."},
                {"6", ".", ".", "1", "9", "5", ".", ".", "."},
                {".", "9", "8", ".", ".", ".", ".", "6", "."},
                {"8", ".", ".", ".", "6", ".", ".", ".", "3"},
                {"4", ".", ".", "8", ".", "3", ".", ".", "1"},
                {"7", ".", ".", ".", "2", ".", ".", ".", "6"},
                {".", "6", ".", ".", ".", ".", "2", "8", "."},
                {".", ".", ".", "4", "1", "9", ".", ".", "5"},
                {".", ".", ".", ".", "8", ".", ".", "7", "9"}
        };
        char[][] board = new char[boardString.length][boardString[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = boardString[i][j].charAt(0);
            }
        }
        new Solution().solveSudoku(board);
    }

    class Solution {
        public void solveSudoku(char[][] board) {
            check(board);
        }

        private boolean check(char[][] board) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == '.') {
                        for (int k = 1; k <= 9; k++) {
                            if (!checkValid(board, i, j, k)) {
                                continue;
                            }
                            board[i][j] = (char) ('0' + k);
                            if (check(board)) {
                                return true;
                            }
                        }
                        board[i][j] = '.';
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean checkValid(char[][] board, int row, int col, int k) {
            char c = (char) (k + '0');
            for (int i = 0; i < 9; i++) {
                if (board[row][i] == c) {
                    return false;
                }
                if (board[i][col] == c) {
                    return false;
                }
            }

            int newRow = row / 3 * 3;
            int newCol = col / 3 * 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[newRow + i][newCol + j] == c) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
