package jl.multithread;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class ThreadLocalUtils {
    private static final ThreadLocal<String> THREAD_LOCAL_MAP = new ThreadLocal<>();

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = new ThreadPoolExecutor(2, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        for (int i = 0; i < 20; i++) {
            executorService.submit(new MyClass(i));
        }

        Thread.sleep(3000);
        for (int i = 21; i < 40; i++) {
            executorService.submit(new MyClass(i));
        }
    }

    private static void process() {

    }

    private static class MyClass implements Callable<String> {

        private final int num;

        MyClass(int num) {
            this.num = num;
        }

        @Override
        public String call() throws Exception {
            log.info("hello world:{}", num);
            THREAD_LOCAL_MAP.set(String.valueOf(num));
            Thread.sleep(1000);
            return String.valueOf(num);
        }
    }
}
