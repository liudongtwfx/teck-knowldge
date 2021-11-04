package jl.multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

public class QueueUtils {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        delayQueue();
    }

    private static void delayQueue() {
        DelayQueue<DelayedWrapper> delayQueue = new DelayQueue<>();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                delayQueue.offer(new DelayedWrapper(i));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    DelayedWrapper poll = delayQueue.poll();
                    System.out.println(poll == null ? "null" : poll.value);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }

            }
        }).start();
    }

    private void fun() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
        List<CompletableFuture<Void>> completableFuture = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(new QueueAdd(queue, i), executorService);
            completableFuture.add(future);
        }
        CompletableFuture.allOf(completableFuture.toArray(new CompletableFuture[]{})).get();
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
        System.out.println(0);
    }

    private static class DelayedWrapper implements Delayed {
        private final long startTime;
        private final int value;

        DelayedWrapper(int value) {
            this.value = value;
            startTime = System.currentTimeMillis() + 1000;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(System.currentTimeMillis() - startTime, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS));
        }
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
