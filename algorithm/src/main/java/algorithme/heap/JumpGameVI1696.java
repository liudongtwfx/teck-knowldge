package algorithme.heap;

import java.util.*;

public class JumpGameVI1696 {
    public static void main(String[] args) {
        new JumpGameVI1696().bridge();
    }

    private void bridge() {
        int[] nums = {1, -1, -2, 4, -7, 3};
        int k = 2;
        System.out.println(new Solution().maxResult(nums, k));
    }


    class Solution {
        public int maxResult(int[] nums, int k) {
            //这道题是dp和单调队列相结合的解法. dp储存的是到当前这个index为止（nums的index)所得到的最大sum。 比如例子1里到-2的index, 它可以是1的位置调过来也可以是-1的位置调过来， 想象一下如果1前面还有无数个数,dp到1对应的index就是到1位置最大的sum, dp到-1就是到-1位置最大的sum. 要想计算到-2的时候sum的最大值， 要么从1位置的dp跳过来， 要么从-1位置的dp跳过来。 当然选择其中大的那个
            int N = nums.length;
            int[] dp = new int[N];//dp储存的是到当前这个index为止（nums的index)所得到的最大sum
            LinkedList<Integer> q = new LinkedList();
            for(int i=0; i<N; i++){//这里注意一下， 最大是到N-1, 是因为for loop 最后有个dp[i+1], dp总共只有N个元素。 所以这里遍历到<N-1
                dp[i] =nums[i];
                if(!q.isEmpty()) dp[i] +=dp[q.peekFirst()];
                while(!q.isEmpty() && i-q.peekFirst()>=k) q.pollFirst();//如果两个元素的index之差超过了k步， 出q, 因为这样下面i进q之后一定不会超过k
                while(!q.isEmpty() && dp[q.peekLast()]<=dp[i]) q.pollLast();//维持递减队列， 出q
                q.offerLast(i);//从队尾加元素
                //dp[i+1] = dp[q.peekFirst()] + nums[i+1];//这里注意i已经进入了滑动窗口， 比如例子2里10， -4， -2已经是在窗口里了， 因为i作为-2已经进去了。 这时候要更新的是下一个4加上递减队列的首位最大的10， 因为经过判断
            }
            return dp[N-1];
        }
    }

}
