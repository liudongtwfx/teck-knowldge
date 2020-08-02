package jl.multithread;

import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

public class HashMapNotSafeDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(String.valueOf(011));
        for (int i = 0; i < 100; i++) {
            new Thread(new MapPutRunner(i * 100, (i + 1) * 100)).start();
        }

        Thread.sleep(3000);
        System.out.println(MapPutRunner.NUM_MAP.size());
    }


    private static class MapPutRunner implements Runnable {
        private static final Map<Count, Integer> NUM_MAP = new HashMap<>();
        private final int start;
        private final int end;

        public MapPutRunner(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @SneakyThrows
        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                NUM_MAP.put(new Count(i), i);
                Thread.sleep(1);
            }
        }
    }

    private static final class Count {
        private final int count;

        Count(int count) {
            this.count = count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Count count1 = (Count) o;
            return count == count1.count;
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }
}
