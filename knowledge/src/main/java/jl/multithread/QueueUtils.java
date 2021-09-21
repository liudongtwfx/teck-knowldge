package jl.multithread;

import java.util.*;
import java.util.concurrent.*;

public class QueueUtils {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
        List<CompletableFuture<Void>> completableFuture = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(new QueueAdd(queue, i), executorService);
            completableFuture.add(future);
        }
        CompletableFuture.allOf(completableFuture.toArray(new CompletableFuture[]{})).get();
        while(!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
        System.out.println(0);
    }

    static class QueueAdd implements Runnable {
        private final Queue<Integer> queue;
        private final Integer num;

        QueueAdd(Queue<Integer> queue, Integer num) {
            this.queue = queue;
            this.num = num;
        }

        @Override
        public void run() {
            try {
                //Thread.sleep(new Random().nextInt(100));
                Thread.sleep(1);
                queue.add(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
