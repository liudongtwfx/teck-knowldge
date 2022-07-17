package algorithme.unionfind;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SatisfiabilityOfEqualityEquations990 {
    public static void main(String[] args) {

    }

    class Solution {
        public boolean equationsPossible(String[] equations) {
            return false;
        }
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        return Arrays.stream(arr).boxed()
                .sorted(Comparator.comparingInt(o -> Math.abs(o - x)))
                .limit(k).collect(Collectors.toList());
    }
}
