package algorithme.string;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels763 {
    public static void main(String[] args) {
        new PartitionLabels763().bridge();
    }

    private void bridge() {
        String s = "ababcbacadefegdehijhklij";
        System.out.println(new Solution().partitionLabels(s));
    }

    class Solution {
        public List<Integer> partitionLabels(String s) {
            int[] lastIndex = new int[26];
            char[] chars = s.toCharArray();
            for (int i = chars.length - 1; i >= 0; i--) {
                if (lastIndex[chars[i] - 'a'] == 0) {
                    lastIndex[chars[i] - 'a'] = i;
                }
            }

            List<Integer> tmp = new ArrayList<>();
            int curr = -1;
            for (int i = 0; i < chars.length; i++) {
                if (i > curr) {
                    tmp.add(curr);
                    curr = i;
                }
                curr = Math.max(curr, lastIndex[chars[i] - 'a']);
            }
            tmp.add(curr);
            List<Integer> ans = new ArrayList<>();
            for (int i = 1; i < tmp.size(); i++) {
                ans.add(tmp.get(i) - tmp.get(i - 1));
            }
            return ans;
        }
    }
}
