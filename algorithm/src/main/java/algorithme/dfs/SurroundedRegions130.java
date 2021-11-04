package algorithme.dfs;

public class SurroundedRegions130 {
    public static void main(String[] args) {

    }

    class Solution {
        public void solve(char[][] board) {
            int m = board.length;
            int n = board[0].length;
            for (int i = 0; i < m; i++) {
                if (board[i][0] == 'O') {
                    dfs(board, i, 0, new boolean[m][n]);
                }
            }
            for (int i = 0; i < n; i++) {
                if (board[0][i] == 'O') {
                    dfs(board, 0, i, new boolean[m][n]);
                }
            }
            for (int i = 0; i < m; i++) {
                if (board[m - 1][i] == 'O') {
                    dfs(board, m - 1, i, new boolean[m][n]);
                }
            }
            for (int i = 0; i < n; i++) {
                if (board[i][n - 1] == 'O') {
                    dfs(board, i, n - 1, new boolean[m][n]);
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == '1') {
                        board[i][j] = 'O';
                    } else if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                }
            }
        }

        private void dfs(char[][] board, int i, int j, boolean[][] visited) {
            if (i < 0 || j < 0 || i > board.length - 1 || j > board[0].length - 1) {
                return;
            }
            if (visited[i][j] || board[i][j] == 'X') {
                return;
            }
            board[i][j] = '1';
            visited[i][j] = true;
            dfs(board, i + 1, j, visited);
            dfs(board, i, j + 1, visited);
            dfs(board, i - 1, j, visited);
            dfs(board, i, j - 1, visited);
        }
    }
}
