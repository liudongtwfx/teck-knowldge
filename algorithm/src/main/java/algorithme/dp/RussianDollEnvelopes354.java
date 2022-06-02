package algorithme.dp;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.Arrays;

public class RussianDollEnvelopes354 {
    public static void main(String[] args) {
        System.out.println(Integer.valueOf("11111111111111111111111111111101", 2));
        new RussianDollEnvelopes354().bridge();
    }

    private void bridge() {
        String input = "[[10,8],[1,12],[6,15],[2,18]]";
        int[][] envelopes = new Gson().fromJson(input, new TypeToken<int[][]>() {
        }.getType());
        System.out.println(new Solution().maxEnvelopes(envelopes));
    }

    class Solution {
        public int maxEnvelopes(int[][] envelopes) {
            if (envelopes == null || envelopes.length == 0
                    || envelopes[0] == null || envelopes[0].length != 2)
                return 0;
            Arrays.sort(envelopes, (arr1, arr2) -> {
                if (arr1[0] == arr2[0])
                    return arr2[1] - arr1[1];
                else
                    return arr1[0] - arr2[0];
            });
            int dp[] = new int[envelopes.length];
            int len = 0;
            for (int[] envelope : envelopes) {
                int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
                if (index < 0)
                    index = -(index + 1);
                dp[index] = envelope[1];
                if (index == len)
                    len++;
            }
            return len;
        }
    }
}
