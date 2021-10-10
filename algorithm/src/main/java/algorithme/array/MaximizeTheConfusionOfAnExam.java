package algorithme.array;

public class MaximizeTheConfusionOfAnExam {
    public static void main(String[] args) {
        new MaximizeTheConfusionOfAnExam().bridge();
    }


    private void bridge() {
        String s = "TFFT";
        Solution solution = new Solution();
        System.out.println(solution.maxConsecutiveAnswers(s, 1));
    }

    class Solution {
        /**
         * i到j F的数量
         *
         * @param answerKey
         * @param k
         * @return
         */
        public int maxConsecutiveAnswers(String answerKey, int k) {
            return Math.max(getMaxOf(answerKey, k, 'T'), getMaxOf(answerKey, k, 'F'));
        }

        private int getMaxOf(String answerKey, int k, char c) {
            int[] tCountMap = new int[answerKey.length()];
            int[] indexMap = new int[answerKey.length()];
            char[] chars = answerKey.toCharArray();
            int index = 1;
            if (chars[0] == c) tCountMap[0] = 1;
            else indexMap[index++] = 0;
            int ans = 1;
            for (int i = 1; i < chars.length; i++) {
                if (chars[i] == c) {
                    tCountMap[i] = tCountMap[i - 1] + 1;
                } else {
                    indexMap[index++] = i;
                    tCountMap[i] = tCountMap[i - 1];
                }
                int lastIndex = -1;
                if (i + 1 - tCountMap[i] - k >= 0) {
                    lastIndex = indexMap[i + 1 - tCountMap[i] - k];
                }
                ans = Math.max(ans, i - lastIndex);
                System.out.println(ans);
            }
            System.out.println("-------");
            return ans;
        }
    }
}
