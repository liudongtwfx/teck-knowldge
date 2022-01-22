package algorithme.bfs;

import java.util.*;
import java.util.function.ToIntFunction;

public class JumpGameIV1345 {
    public static void main(String[] args) {
        new JumpGameIV1345().bridge();
    }

    private void bridge() {
        int[] arr = new int[50000];
        Arrays.fill(arr, 7);
        System.out.println(new Solution().minJumps(arr));
    }

    class Solution {
        public int minJumps(int[] arr) {
            int length = arr.length;
            Map<Integer, Queue<Integer>> sameIndexMap = new HashMap<>();
            for (int i = 0; i < length; i++) {
                PriorityQueue<Integer> priorityQue = new PriorityQueue<>(Comparator.comparingInt((ToIntFunction<Integer>) value -> value).reversed());
                priorityQue.offer(i);
                sameIndexMap.merge(arr[i], priorityQue, (a, b) -> {
                    a.addAll(b);
                    return a;
                });
            }
            int[] ansDp = new int[length];
            Arrays.fill(ansDp, length - 1);
            ansDp[0] = 0;
            Queue<Integer> indexJump = new LinkedList<>();
            indexJump.offer(0);
            boolean[] process = new boolean[length];
            while (!indexJump.isEmpty()) {
                int headIndex = indexJump.poll();
                Queue<Integer> sameIndex = sameIndexMap.getOrDefault(arr[headIndex], new ArrayDeque<>());
                if (headIndex > 0 && !process[headIndex - 1]) {
                    sameIndex.add(headIndex - 1);
                }
                if (headIndex < length - 1 && !process[headIndex + 1]) {
                    sameIndex.add(headIndex + 1);
                }
                while (!sameIndex.isEmpty()) {
                    Integer index = sameIndex.poll();
                    if (!process[index] && ansDp[index] > ansDp[headIndex]) {
                        ansDp[index] = Math.min(ansDp[index], ansDp[headIndex] + 1);
                        indexJump.offer(index);
                        process[index] = true;
                    }
                }
                sameIndexMap.remove(arr[headIndex]);
            }
            return ansDp[length - 1];
        }
    }
}
