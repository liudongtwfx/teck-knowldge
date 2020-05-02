package algorithme.array;

/**
 * 888. Fair Candy Swap
 * https://leetcode.com/problems/fair-candy-swap/
 */
public class FireCandySwap {
    public static void main(String[] args) {
    }

    class Solution {
        public int[] fairCandySwap(int[] A, int[] B) {
            int aLen = A.length;
            int bLen = B.length;
            int aTotal = 0, bTotal = 0;
            boolean[] exist = new boolean[100001];
            for (int value : A) {
                aTotal += value;
            }
            for (int value : B) {
                bTotal += value;
                exist[value] = true;
            }
            int target = (aTotal + bTotal) / 2;
            for (int value : A) {
                // aTotal - value + bValue =  target   ->  bValue=target+value-aTotal;
                int expected = target + value - aTotal;
                if (expected > 0 && expected <= 100000 && exist[expected]) {
                    return new int[]{value, expected};
                }
            }
            return null;
        }
    }
}
