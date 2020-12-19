package algorithme.dp;

public class WaysToMakeAFairArray {
    public static void main(String[] args) {
        new WaysToMakeAFairArray().bridge();
    }

    private void bridge() {
        int[] nums = {2, 1, 6, 4};
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println(new Solution().waysToMakeFair(nums));
    }

    class Solution {
        public int waysToMakeFair(int[] nums) {
            int length = nums.length;
            int[] rightEvenMap = new int[100001];
            int[] rightOddMap = new int[100001];
            int sum = 0, rightEven = 0;
            for (int i = length - 1; i >= 0; i--) {
                sum += nums[i];
                if ((i & 1) != 1) {
                    rightEven += nums[i];
                }
                rightEvenMap[i] = rightEven;
                rightOddMap[i] = sum - rightEven;
            }
            sum = 0;
            int leftEven = 0, ans = 0;
            for (int i = 0; i < length; i++) {
                int leftOdd = sum - leftEven;
                int rightOdd = i < length - 1 ? rightOddMap[i + 1] : 0;
                rightEven = i < length - 1 ? rightEvenMap[i + 1] : 0;
                if (leftEven + rightEven == leftOdd + rightOdd) {
                    ++ans;
                }
                if ((i & 1) != 1) {
                    leftEven += nums[i];
                }
                sum += nums[i];
            }
            return ans;
        }
    }
}
