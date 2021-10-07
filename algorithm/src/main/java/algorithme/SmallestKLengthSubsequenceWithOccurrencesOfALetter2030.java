package algorithme;

import java.util.*;

public class SmallestKLengthSubsequenceWithOccurrencesOfALetter2030 {
    public static void main(String[] args) {
        new SmallestKLengthSubsequenceWithOccurrencesOfALetter2030().bridge();
    }

    private void bridge() {
        String s = "leet";
        int k = 3;
        char letter = 'e';
        int repetition = 1;
        System.out.println(new Solution().smallestSubsequence(s, k, letter, repetition));
    }

    class Solution {
        public String smallestSubsequence(String s, int k, char letter, int repetition) {
            List<Integer> indices = new ArrayList<Integer>();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == letter) indices.add(i);
            }
            if (indices.size() == repetition) {
                char[] chars = new char[repetition];
                Arrays.fill(chars, letter);
                return new String(chars);
            }
            Queue<Wapper> queue = new PriorityQueue<>();
            Set<Integer> added = new HashSet<>();
            for (int i = 0; i < repetition; i++) {
                int index = indices.get(i);
                queue.offer(new Wapper(index, s.charAt(index)));
                added.add(index);
            }
            int currentLetter = repetition;
            int index = 0;
            while (index < s.length()) {
                if (queue.size() < k) {
                    if (!added.contains(index)) {
                        queue.offer(new Wapper(index, s.charAt(index)));
                        added.add(index);
                    }
                    index++;
                    continue;
                }
                Wapper poll = queue.poll();
                if (poll.c > s.charAt(index)) {
                    if (currentLetter < repetition) {
                        queue.offer(poll);
                    } else {
                        queue.offer(new Wapper(index, s.charAt(index)));
                        if (poll.c == letter) {
                            currentLetter--;
                        }
                        if (s.charAt(index) == letter) {
                            currentLetter++;
                        }
                    }
                } else {
                    queue.offer(poll);
                }
                index++;
            }
            List<Wapper> wrapList = new ArrayList<>(queue);
            wrapList.sort(Comparator.comparingInt(o -> o.index));
            StringBuilder sb = new StringBuilder();
            for (Wapper wapper : wrapList) {
                sb.append(wapper.c);
            }
            return sb.toString();
        }

        class Wapper implements Comparable<Wapper> {
            private final int index;
            private final char c;

            public Wapper(int index, char c) {
                this.index = index;
                this.c = c;
            }

            @Override
            public int compareTo(Wapper o) {
                return this.c - o.c;
            }
        }
    }
}
