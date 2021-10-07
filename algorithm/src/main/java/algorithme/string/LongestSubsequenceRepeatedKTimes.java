package algorithme.string;

import java.util.HashSet;
import java.util.Set;

public class LongestSubsequenceRepeatedKTimes {
    public static void main(String[] args) {
        new LongestSubsequenceRepeatedKTimes().bridge();
    }

    private void bridge() {
//        String s = "exhmepeeeeeekeeetelqyeeeeudtdsjeeyeweeeeekqeizesieqnddzeaefqeyeeezesxfreveeeeyeeeseregoneiftemerujfveysezkeeiofsbeeerheueeehedkluoedeeemeweeekeefeqaleb";
//        int k = 65;
//        System.out.println(s.length());
//        System.out.println(new Solution().longestSubsequenceRepeatedK(s, k));
//
//
//        String s = "bbabbabbbbabaababab";
//        int k = 3;
//        System.out.println(new Solution().longestSubsequenceRepeatedK(s, k));

//        String s = "coqdndqojcqxndojcqndojrcqndojcqndojcqndnojcqzndojcqndojcqnudojcqndvojcqndoj";
//        int k = 11;

        String s = "lvzlvzlvzlvntzjlvzlvzlvzlvgzlbvzlvzlvozlyyvvwwzlhvrzxlvqzlvzzrlevzlvzelvmrdkuzlvzqlvczqlvzlivdzqaolvzzslvzziozlvtbzlhiktvgzlvzlvzlvuzdlpvzlvzlbvzstulcypvzlvzlvzlvzilhvuzlvzlvzxlvuzmplnjvzlvkyzulwvzlvlzlvzalvfeyzlvrezlunivmzzzlvzlvzexlvzcklvzlvzlllklhkvzclvzqzlvmzjldpvzlvjzlqluvevzylvjzglvzlvzlsvkgdzglobvzahhlomvzlebvzzlvztlvzlvvzlvzlvzlvzzlvzlvzlivlalziylcvvzosllxvzlvzvvilvzilvfwzlvzlzbzvzwlmagdvhyizxlvkmzlvzelvzilvzxlvwyazlvzlvtzlvstuzlvuzlvjzlvdilwzlvzladevzuzlsczrzvtzlfvvdzlidpvzlvrezdlvzolvzlfvzsltvzrzlxvzlvjozrlvzsluvzbrlvzclvhzlmfvzlvzlsefpvzlvvfzlvszlvztlhvzlgivzlvzllkvzlvvznkblxijqvgzhzlvzylvzlcnvcpxzlvzlvkwwozlvzklvzzltvzlvzlvzlvzlxkvzlvtzlivzlvatzlvvivszwlvzlvhzzxlvzklvzplvzlvzlvqzzlvgzhluvzlvzlvzlvzilvzvvclvjvzlclvvzlvzlvklzlvzmlovrzxlvzlvjzlsvzlbvzaflvznlvnzzldvzdvvglvezlvtzlvzlrzefvzlvztlvzlqvzulwvlzpnmllvvhzclvzlvzlvwzlvslzjldvzzklfehvzljvzmilnyvuyzlvrllzclvzlvzlavzrsplcvzlvzildpvzlvzlv";
        int k = 189;
        System.out.println(new Solution().longestSubsequenceRepeatedK(s, k));

//        String s = "gggggxgggggggggggggaggrgoggggggtgggvggggggggggggggvpiggggggggggggggzagggtgggggsggggggrnggqgggggggdgggkgggggggggggggaggggggrggggggggggiggfgggmgggggggggggggdggcgggyopggggggggggggggggggbggggggggvfgggggggggggg";
//        int k = 86;
//        System.out.println(new Solution().longestSubsequenceRepeatedK(s, k));
    }

    class Solution {
        public String longestSubsequenceRepeatedK(String s, int k) {
            Set<String> dp = new HashSet<>();
            String ans = "";
            Set<String> computed = new HashSet<>();
            for (int i = 0; i < s.length(); i++) {
                System.out.println(i + "  " + dp.size());
                char c = s.charAt(i);
                Set<String> newDp = new HashSet<>();
                String single = String.valueOf(c);
                newDp.add(single);
                if (!computed.contains(single) && !compareAns(ans, single).equals(ans) && dfs(s, i + 1, single) >= k - 1) {
                    ans = compareAns(ans, single);
                    computed.add(single);
                }
                for (String s1 : dp) {
                    if (s1.length() < 7 && (s.length() - i) >= s1.length() * (k - 1)) {
                        newDp.add(s1);
                    }
                    String s2 = s1 + c;
                    if (s1.length() < 7 && (s.length() - i) >= s2.length() * (k - 1) && !computed.contains(s2)) {
                        int count = dfs(s, i + 1, s2);
                        System.out.println(i + " " + s2 + " " + count);
                        if (count >= k - 1) {
                            ans = compareAns(ans, s2);
                        }
                        if (count >= k - 1) {
                            newDp.add(s2);
                        }
                        computed.add(s2);
                    }
                }
                dp = newDp;
            }
            return ans;
        }

        private String compareAns(String ans, String another) {
            // System.out.println(another);
            if (another.length() == ans.length()) {
                return ans.compareTo(another) <= 0 ? another : ans;
            }
            if (another.length() > ans.length()) {
                return another;
            }
            return ans;
        }

        private int dfs(String s, int start, String sub) {
            if (start >= s.length()) {
                return 0;
            }
            int indexLeft = start;
            int rightIndex = 0;
            while (rightIndex < sub.length()) {
                while (indexLeft < s.length() && s.charAt(indexLeft) != sub.charAt(rightIndex)) {
                    indexLeft++;
                }
                if (indexLeft >= s.length() && rightIndex <= sub.length() - 1) {
                    return 0;
                }
                indexLeft++;
                rightIndex++;
            }
            return 1 + dfs(s, indexLeft, sub);
        }
    }
}
