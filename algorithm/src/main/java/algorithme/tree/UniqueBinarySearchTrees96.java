package algorithme.tree;

public class UniqueBinarySearchTrees96 {
    public static void main(String[] args) {
        new UniqueBinarySearchTrees96().bridge();
    }

    private void bridge() {
        System.out.println(new Solution().numTrees(5));
    }

    class Solution {
        private int[] values = new int[20];

        public int numTrees(int n) {
            if (n <= 2) {
                return n;
            }
            if (values[n] != 0) {
                return values[n];
            }
            int ans = 0;
            System.out.println(n);
            for (int i = 1; i < n; i++) {
                ans += numTrees(i) * numTrees(n - i - 1);
            }
            values[n] = ans;
            return ans;
        }
    }
}
