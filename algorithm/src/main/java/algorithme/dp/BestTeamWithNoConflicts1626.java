package algorithme.dp;

import java.util.Arrays;
import java.util.Comparator;

public class BestTeamWithNoConflicts1626 {
    public static void main(String[] args) {
        new BestTeamWithNoConflicts1626().bridge();
    }

    private void bridge() {
        int[] scores = {1, 3, 5, 10, 15};
        int[] ages = {1, 2, 3, 4, 5};
        System.out.println(new Solution().bestTeamScore(scores, ages));

        int[] scores1 = {1, 2, 3, 5};
        int[] ages1 = {8, 9, 10, 1};
        System.out.println(new Solution().bestTeamScore(scores1, ages1));
    }

    class Solution {
        public int bestTeamScore(int[] scores, int[] ages) {
            int[][] scoreAndAge = new int[scores.length][2];
            for (int i = 0; i < scores.length; i++) {
                scoreAndAge[i][0] = scores[i];
                scoreAndAge[i][1] = ages[i];
            }
            Arrays.sort(scoreAndAge, new AgeCmp());
            int[] ans = new int[scores.length];
            int res = 0;
            for (int i = 0; i < scoreAndAge.length; i++) {
                ans[i] = scoreAndAge[i][0];
                for (int j = 0; j < i; j++) {
                    //ans[i] = Math.max(ans[i], ans[j]);
                    if (scoreAndAge[j][1] == scoreAndAge[i][1] || scoreAndAge[j][0] < scoreAndAge[i][0]) {
                        ans[i] = Math.max(ans[i], ans[j] + scoreAndAge[i][0]);
                    }
                }
                res = Math.max(res, ans[i]);
            }
            return res;
        }

        class AgeCmp implements Comparator<int[]> {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        }
    }
}
