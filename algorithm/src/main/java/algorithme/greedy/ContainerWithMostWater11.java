package algorithme.greedy;

public class ContainerWithMostWater11 {
    public static void main(String[] args) {

    }

    class Solution {
        public int maxArea(int[] height) {
            int ans = 0;
            int i = 0, j = height.length - 1;
            while (i < j) {
                if (height[i] <= height[j]) {
                    ans = Math.max(ans, height[i] * (j - i));
                    i++;
                    continue;
                }
                ans = Math.max(ans, height[j] * (j - i));
                j--;
            }
            return ans;
        }
    }
}
