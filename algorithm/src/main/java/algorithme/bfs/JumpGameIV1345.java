package algorithme.bfs;

import java.util.*;

public class JumpGameIV1345 {
    public static void main(String[] args) {

    }

    class Solution {
        public int minJumps(int[] arr) {
            Map<Integer, List<Integer>> sameIndexMap = new HashMap<>();
            for (int i : arr) {
                sameIndexMap.merge(i, new ArrayList<>(Collections.singletonList(i)), (a, b) -> {
                    a.addAll(b);
                    return a;
                });
            }

        }
    }
}
