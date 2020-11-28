package algorithme.dfs;

public class MaximizeGridHappiness1659 {
    public static void main(String[] args) {
        new MaximizeGridHappiness1659().bridge();
    }


    private void bridge() {
        long start = System.currentTimeMillis();
        new Solution().getMaxGridHappiness(3, 4, 4, 3);
        System.out.println(System.currentTimeMillis() - start);
    }

    class Solution {
        int[][][][] dp;
        int[][] scoreMap;
        int[] scorePeople = {0, 120, 40};

        public int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
            dp = new int[m * n][244][introvertsCount + 1][extrovertsCount + 1];
            scoreMap = new int[3][3];
            scoreMap[0][1] = 120;
            scoreMap[0][2] = 40;
            scoreMap[1][1] = 60;
            scoreMap[1][2] = 30;
            scoreMap[2][1] = 110;
            scoreMap[2][2] = 80;
            int ans = 0;
            int state = (int) Math.pow(3.0, n);
            for (int j = 0; j < state; j++) {
                int intro = getPeopleNum(j, 1);
                int extro = getPeopleNum(j, 2);
                if (intro > introvertsCount || extro > extrovertsCount) {
                    continue;
                }
                dp[n - 1][j][intro][extro] = getScore(j);
                ans = Math.max(ans, dp[n - 1][j][intro][extro]);
            }
            for (int i = n; i < m * n; i++) {
                for (int j = 0; j < state; j++) {
                    int intro = getPeopleNum(j, 1);
                    int extro = getPeopleNum(j, 2);
                    int lastTwoScore = n >= 2 ? scoreMap[getPeople(j, 1)][getPeople(j, 0)] : 0;
                    for (int in = intro; in <= introvertsCount; in++) {
                        for (int ex = extro; ex <= extrovertsCount; ex++) {
                            for (int first = 0; first < 3; first++) {
                                int lastState = j / 3 + first * state / 3;
                                int lastPeople = getPeople(j, 0);
                                int lastScore = dp[i - 1][lastState][in - ((lastPeople == 1) ? 1 : 0)][ex - ((lastPeople == 2) ? 1 : 0)];
                                if (lastScore == 0 && lastState != 0) {
                                    continue;
                                }
                                if (i % n == 0) {
                                    lastScore += scoreMap[first][lastPeople];
                                } else {
                                    lastScore += lastTwoScore + scoreMap[first][lastPeople] - scorePeople[lastPeople];
                                }
                                dp[i][j][in][ex] = Math.max(dp[i][j][in][ex], lastScore);
                                ans = Math.max(ans, dp[i][j][in][ex]);
                            }
                        }
                    }
                }
            }

            return ans;
        }

        public int getPeopleNum(int state, int p) {
            int cnt = 0;
            while (state > 0) {
                if (state % 3 == p) {
                    cnt++;
                }
                state /= 3;
            }
            return cnt;
        }

        public int getPeople(int state, int index) {
            for (int i = 0; i < index; i++) {
                state /= 3;
            }
            return state % 3;
        }

        public int getScore(int state) {
            int last = 0;
            int score = 0;
            while (state > 0) {
                int cur = state % 3;
                score += scoreMap[last][cur];
                last = cur;
                state /= 3;
            }
            return score;
        }
    }
}
