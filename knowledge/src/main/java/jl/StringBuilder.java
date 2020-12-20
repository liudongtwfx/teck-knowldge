package jl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class StringBuilder {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(1, 10, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<Integer> submit = executorService.submit(new Task(), i);
            futures.add(submit);
        }

        for (int i = 0; i < futures.size(); i++) {
            try {
                int ans = futures.get(i).get(2, TimeUnit.SECONDS);
                System.out.println(ans);
            } catch (TimeoutException toe) {
                System.out.println("i " + i + " is canceled");
                futures.get(i).cancel(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                // e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
