package algorithme.array;

public class AddMinimumNumberOfRungs {
    public static void main(String[] args) {
        new AddMinimumNumberOfRungs().bridge();
    }

    private void bridge() {
        int[] rungs = {3};
        System.out.println(new Solution().addRungs(rungs, 1));
    }

    class Solution {
        public int addRungs(int[] rungs, int dist) {
            int current = 0;
            int index = 0, ans = 0;
            while (index < rungs.length) {
                while (index < rungs.length && rungs[index] <= current + dist) {
                    current = rungs[index];
                    index++;
                }
                if (current >= rungs[rungs.length - 1]) {
                    return ans;
                }
                int count = ceil(rungs[index] - current, dist);
                ans += count;
                current += count * dist;
            }
            return ans;
        }

        private int ceil(int a, int b) {
            if (a % b == 0) {
                return a / b - 1;
            }
            return a / b;
        }
    }
}
