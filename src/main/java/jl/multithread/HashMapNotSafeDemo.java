package jl.multithread;

import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class HashMapNotSafeDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            new Thread(new MapPutRunner(i * 100, (i + 1) * 100, countDownLatch)).start();
        }

        countDownLatch.await(3000, TimeUnit.MILLISECONDS);
        System.out.println(MapPutRunner.NUM_MAP.size());
    }


    private static class MapPutRunner implements Runnable {
        private static final Map<Count, Integer> NUM_MAP = new ConcurrentHashMap<>();
        private final int start;
        private final int end;
        private final CountDownLatch countDownLatch;

        public MapPutRunner(int start, int end, CountDownLatch countDownLatch) {
            this.start = start;
            this.end = end;
            this.countDownLatch = countDownLatch;
        }

        @SneakyThrows
        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                NUM_MAP.put(new Count(i), i);
                Thread.sleep(1);
            }
            countDownLatch.countDown();
        }
    }

    private static final class Count {
        private final int count;

        Count(int count) {
            this.count = count;
        }

        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Count count1 = (Count) o;
            return count == count1.count;
        }
    }
}
