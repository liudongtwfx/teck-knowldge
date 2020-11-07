package algorithme.array;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 *
 */
@Slf4j
public class FindTheMinimumNumberOfFibonacciNumbersWhoseSumIsK {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = new ThreadPoolExecutor(2, 50, 5000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(50), r -> {
            Thread thread = new Thread(r);
            System.out.println(thread.getName());
            return thread;
        });

        for (int i = 0; i < 100; i++) {
            final String s = String.valueOf(i);
            executorService.submit(() -> {
                log.info("hello world" + s);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Thread.sleep(100);
        }
    }

    class Solution {
        public int findMinFibonacciNumbers(int k) {
            List<Integer> nums = new ArrayList<>();
            nums.add(1);
            nums.add(1);
            int last = 1;
            while (last <= k) {
                if (last == k) {
                    return 1;
                }
                last = nums.get(nums.size() - 2) + nums.get(nums.size() - 1);
                nums.add(last);
            }
            return dfs(k, nums, nums.size() - 1);
        }

        private int dfs(int current, List<Integer> nums, int start) {
            for (int i = start; i >= 0; i--) {
                int n = nums.get(i);
                if (n > current) {
                    continue;
                }
                if (n == current) {
                    return 1;
                }
                int c = dfs(current - n, nums, start - 1);

                if (c > 0) {
                    return c + 1;
                }
            }
            return 0;
        }
    }
}
