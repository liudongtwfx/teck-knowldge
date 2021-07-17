package algorithme;

import java.util.*;

public class MaximumValueAfterInsertion1881 {
    public static void main(String[] args) throws Exception {
        int[] nums = {-1, 2, -2, 2, 1, 2, 1, 2, 2, -2, 1};
        int ans = getCountEqK(nums, 4);
        System.out.println(ans);
    }

    public static int getCountEqK(int[] nums, int k) {
        Map<Integer, Integer> tmp = new HashMap<>();
        Map<Integer, List<Count>> tmpCount = new HashMap<>();
        int sum = 0, res = 0;
        tmp.put(0, 1);
        List<Count> counts = new ArrayList<>();
        // sum(0,j)-sum(0,i)==k <- nums[i..j]==k
        // sum(0,j)-k= sum(0,i)
        // 1 -1 1 -1
        counts.add(new Count(-1, 0));
        tmpCount.put(0, counts);
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            sum += num;
            int prevCount = tmp.getOrDefault(sum, 0);
            tmp.put(sum, prevCount + 1);
            List<Count> orDefault = tmpCount.getOrDefault(sum, new ArrayList<>());
            orDefault.add(new Count(i, num));
            tmpCount.put(sum, orDefault);
            int prv = sum - k;
            res += tmp.getOrDefault(prv, 0);
            for (Count count : tmpCount.getOrDefault(prv, Collections.emptyList())) {
                for (int j = count.index + 1; j <= i; j++) {
                    System.out.print(nums[j] + " ");
                }
                System.out.println();
            }
        }
        return res;
    }

    static class Count {
        int index;
        int val;

        public int getIndex() {
            return index;
        }

        public Count(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }
}
