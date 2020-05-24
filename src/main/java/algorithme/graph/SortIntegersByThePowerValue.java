package algorithme.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/sort-integers-by-the-power-value/
 * <p>
 * 1387. Sort Integers by The Power Value
 */
public class SortIntegersByThePowerValue {
    public static void main(String[] args) {
        new SortIntegersByThePowerValue().bridge();
    }

    private void bridge() {
        int lo = 1;
        int hi = 1000;
        int k = 777;
        System.out.println(new Solution().getKth(lo, hi, k));
    }

    class Solution {
        int[] cache = new int[10000];

        public int getKth(int lo, int hi, int k) {
            List<NumCount> nums = new ArrayList<>();
            for (int i = lo; i <= hi; i++) {
                nums.add(new NumCount(getPow(i), i));
            }
            nums.sort((o1, o2) -> {
                if (o1.count != o2.count) {
                    return o1.count - o2.count;
                }
                return o1.num.compareTo(o2.num);
            });
            return nums.get(k - 1).num;
        }


        private int getPow(int num) {
            if (num == 1) {
                return 1;
            }
            if (num <= 10000 && cache[num] != 0) {
                return cache[num];
            }
            int ans = 0;
            if (num % 2 == 0) {
                ans = 1 + getPow(num / 2);
            } else {
                ans = 1 + getPow(num * 3 + 1);
            }
            if (num < 10000) {
                cache[num] = ans;
            }
            return cache[num];
        }

        class NumCount {
            int count;
            Integer num;

            public NumCount(int count, int num) {
                this.count = count;
                this.num = num;
            }
        }
    }
}
