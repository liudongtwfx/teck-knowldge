package algorithme.dfs;

import java.util.*;


public class TheScoreOfStudentsSolvingMathExpression2019 {

    public static void main(String[] args) {
        new TheScoreOfStudentsSolvingMathExpression2019().bridge();
    }

    private void bridge() {
        String s = "3+3*6+3*6+9*6+9*3+3*3+6*3+9*3+9";
        int[] answers = {42, 17, 13, 66, 32, 547, 21, 90, 13, 33, 45, 66, 34, 21, 13, 13, 13, 46, 21, 177, 18, 13, 18, 16, 16, 678, 13, 42, 66, 13, 18, 18, 777, 21, 924, 13, 268, 13, 13, 13, 25, 62, 45, 33, 888, 779, 13, 206, 48, 13, 34, 17};
        System.out.println(new Solution().scoreOfStudents(s, answers));
    }

    /**
     * String s="7+3*1*2";
     */
    class Solution {
        private int correct = 0;
        private Map<String, Integer[]> dpMap = new HashMap<>();

        public int scoreOfStudents(String s, int[] answers) {
            computeCorrect(s);
            Set<Integer> computedAnswers = new HashSet<>(Arrays.asList(computeSub(s, 0, s.length())));
            // System.out.println(computedAnswers.stream().map(String::valueOf).collect(Collectors.joining(",")));
            // System.out.println(correct);
            int ans = 0;
            for (Integer answer : answers) {
                if (answer == correct) {
                    ans += 5;
                    continue;
                }
                if (computedAnswers.contains(answer)) {
                    ans += 2;
                }
            }
            dpMap.forEach((k, v) -> System.out.printf("key:%s\n", k));
            System.out.println(dpMap.size());
            return ans;
        }

        private Integer[] computeSub(String s, int start, int end) {
            if (start >= end) {
                return new Integer[]{};
            }
            String key = start + "_" + end;
            if (dpMap.containsKey(key)) {
                return dpMap.get(key);
            }
            boolean contains = false;
            Set<Integer> answer = new HashSet<>();
            for (int i = start; i < end; i++) {
                char c = s.charAt(i);
                if (c == '+' || c == '*') {
                    contains = true;
                    Integer[] leftAll = computeSub(s, start, i);
                    Integer[] rightAll = computeSub(s, i + 1, end);
                    for (Integer integer : leftAll) {
                        for (Integer value : rightAll) {
                            int v = (c == '+' ? integer + value : integer * value);
                            if (v <= 1000) {
                                answer.add(v);
                            }
                        }
                    }
                }
            }
            if (!contains) {
                answer.add(Integer.parseInt(s.substring(start, end)));
            }
            Integer[] integers = answer.toArray(new Integer[0]);
            dpMap.put(key, integers);
            return integers;
        }

        private void computeCorrect(String s) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '*') {
                    int[] nearPair = getNearPair(s, i);
                    int after = nearPair[0] * nearPair[1];
                    computeCorrect(s.substring(0, nearPair[2] + 1) + after + s.substring(nearPair[3]));
                    return;
                }
            }
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '+') {
                    int[] nearPair = getNearPair(s, i);
                    int after = nearPair[0] + nearPair[1];
                    computeCorrect(s.substring(0, nearPair[2] + 1) + after + s.substring(nearPair[3]));
                    return;
                }
            }
            correct = Integer.parseInt(s);
        }

        private int[] getNearPair(String s, int pos) {
            int i = pos - 1;
            StringBuilder str = new StringBuilder();
            while (i >= 0) {
                if (s.charAt(i) == '+' || s.charAt(i) == '*') {
                    break;
                }
                str.insert(0, s.charAt(i));
                i--;
            }
            int rightPos = pos + 1;
            StringBuilder rightStr = new StringBuilder();
            while (rightPos < s.length()) {
                if (s.charAt(rightPos) == '+' || s.charAt(rightPos) == '*') {
                    break;
                }
                rightStr.append(s.charAt(rightPos));
                rightPos++;
            }
            return new int[]{Integer.parseInt(str.toString()), Integer.parseInt(rightStr.toString()), i, rightPos};
        }
    }
}
