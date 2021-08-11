package algorithme;

public class LargestNumberAfterMutatingSubstring {

    public static void main(String[] args) {
        new LargestNumberAfterMutatingSubstring().bridge();
    }

    private void bridge() {
        String num = "132";
        int[] change = {9, 8, 5, 0, 3, 6, 4, 2, 6, 8};
        System.out.println(new Solution().maximumNumber(num, change));
    }

    class Solution {
        public String maximumNumber(String num, int[] change) {
            char[] chars = num.toCharArray();
            String max = num;
            int index = 0;
            while (index < chars.length) {
                int j = index;
                StringBuilder tmp = new StringBuilder();
                while (j < chars.length) {
                    int n = chars[j] - '0';
                    if (change[n] < n) {
                        break;
                    }
                    tmp.append(change[n]);
                    j++;
                }
                if (j == index) {
                    index++;
                    continue;
                }
                String tmpTotal = num.substring(0, index) + tmp + num.substring(j);
                if (max.compareTo(tmpTotal) <= 0) {
                    max = tmpTotal;
                    return max;
                }
                index = j + 1;
            }
            return max;
        }
    }
}
