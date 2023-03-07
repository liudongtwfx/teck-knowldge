package vqc.domain;

import java.util.*;
import java.util.stream.Collectors;

public class QualificationTemplate {
    class Solution {
        public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
            Set<String> positive = Arrays.stream(positive_feedback).collect(Collectors.toSet());
            Set<String> negative = Arrays.stream(negative_feedback).collect(Collectors.toSet());
            Queue<int[]> maxScoreQueue = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if(o1[0]!=o2[0]) return o2[0] - o1[0];
                    return o1[1] - o2[1];
                }
            });
            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < report.length; i++) {
                String[] words = report[i].split(" ");
                int score = 0;
                for (String word : words) {
                    if (positive.contains(word)) {
                        score += 3;
                    } else if (negative.contains(word)) {
                        score -= 1;
                    }
                }
                maxScoreQueue.offer(new int[]{score, student_id[i]});
            }
            while (k-- > 0 && !maxScoreQueue.isEmpty()) {
                ans.add(maxScoreQueue.poll()[1]);
            }
            return ans;
        }
    }
}
