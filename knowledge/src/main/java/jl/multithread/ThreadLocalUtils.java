package jl.multithread;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class ThreadLocalUtils {
    private static final ThreadLocal<String> THREAD_LOCAL_MAP = new ThreadLocal<>();
    private static int count = 0;
    private static final ThreadFactory THREAD_FACTORY = Executors.defaultThreadFactory();
    private static final int COUNT_BITS = Integer.SIZE - 3;

    public static void main(String[] args) throws Exception {
        System.out.println(0 << COUNT_BITS);
        System.out.println(-536870910 < (0 << COUNT_BITS));
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(2, 10, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                System.out.println("thread count : " + (++count));
                return THREAD_FACTORY.newThread(r);
            }
        });
        for (int i = 0; i < 20; i++) {
            executorService.submit(new MyClass(i));
        }

        Thread.sleep(5000);

        System.out.println("active count " + executorService.getPoolSize());
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
