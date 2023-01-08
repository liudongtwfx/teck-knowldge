package algorithme.dfs;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ValidSudoku36 {
    public static void main(String[] args) {
        new ValidSudoku36().bridge();
    }

    private void bridge() {
        String data = "[[\".\",\"8\",\"7\",\"6\",\"5\",\"4\",\"3\",\"2\",\"1\"],[\"2\",\".\",\".\",\".\",\".\",\".\",\".\",\".\",\".\"],[\"3\",\".\",\".\",\".\",\".\",\".\",\".\",\".\",\".\"],[\"4\",\".\",\".\",\".\",\".\",\".\",\".\",\".\",\".\"],[\"5\",\".\",\".\",\".\",\".\",\".\",\".\",\".\",\".\"],[\"6\",\".\",\".\",\".\",\".\",\".\",\".\",\".\",\".\"],[\"7\",\".\",\".\",\".\",\".\",\".\",\".\",\".\",\".\"],[\"8\",\".\",\".\",\".\",\".\",\".\",\".\",\".\",\".\"],[\"9\",\".\",\".\",\".\",\".\",\".\",\".\",\".\",\".\"]]";
        char[][] board = new Gson().fromJson(data, new TypeToken<char[][]>() {
        }.getType());
        List<int[]> countList = new ArrayList<>();
        Collections.sort(countList, Comparator.comparingInt(a -> a[1]));
        System.out.println(new Solution().isValidSudoku(board));
    }

    class Solution {
        public boolean isValidSudoku(char[][] board) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] != '.') {
                        boolean exist = getExist(board, i, j);
                        if (exist) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }

        private boolean getExist(char[][] board, int i, int j) {
            boolean[] exist = new boolean[9];
            for (int m = 0; m < 9; m++) {
                if (board[m][j] == '.') {
                    continue;
                }
                if (exist[board[m][j] - '1']) {
                    return true;
                }
                exist[board[m][j] - '1'] = true;
            }
            exist = new boolean[9];
            for (int m = 0; m < 9; m++) {
                if (board[i][m] == '.') {
                    continue;
                }
                if (exist[board[i][m] - '1']) {
                    return true;
                }
                exist[board[i][m] - '1'] = true;
            }
            int m = (i / 3) * 3;
            int l = (j / 3) * 3;
            exist = new boolean[9];
            for (int x = m; x < m + 3; x++) {
                for (int y = l; y < l + 3; y++) {
                    if (board[x][y] == '.') {
                        continue;
                    }
                    if (exist[board[x][y] - '1']) {
                        return true;
                    }
                    exist[board[x][y] - '1'] = true;
                }
            }
            return false;
        }
    }
}
