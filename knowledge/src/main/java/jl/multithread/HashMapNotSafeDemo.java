package jl.multithread;

import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HashMapNotSafeDemo {
    public static void main(String[] args) throws Exception {
        System.out.println(-20 >> 2);
        int a = -20 >>> 2;
        System.out.println(a);
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(-4));
    }

    private static void sameInfoDemo() throws InterruptedException {
        final Map<Integer, Integer> map = new HashMap<>(1);
        ExecutorService executor = Executors.newFixedThreadPool(256);
        for (int i = 0; i < 10000; i++) {
            final int j = i;
            executor.submit(() -> {
                try {
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                map.put(j, j);
            });
        }
        Thread.sleep(100);
        System.out.println(map.size());
        System.out.println(map.entrySet().size());
        // map.forEach((k, v) -> System.out.printf("%s:%s\n", k, v));
    }

    private void multiNumber() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            new Thread(new MapPutRunner(i * 100, (i + 1) * 100, countDownLatch)).start();
        }

        boolean await = countDownLatch.await(3000, TimeUnit.MILLISECONDS);
        System.out.println(MapPutRunner.NUM_MAP.size());
    }

    private static class MapPutRunner implements Runnable {
        private static final Map<Count, Integer> NUM_MAP = new HashMap<>();
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
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Count count1 = (Count) o;
            return count == count1.count;
        }
    }
}
