package jl.multithread;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class ThreadLocalUtils {
    private static final ThreadLocal<String> THREAD_LOCAL_MAP = new InheritableThreadLocal<>();
    private static int count = 0;
    private static final ThreadFactory THREAD_FACTORY = Executors.defaultThreadFactory();

    public static void main(String[] args) throws Exception {
        THREAD_LOCAL_MAP.set("hello world");
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(2,
                10,
                10,
                TimeUnit.MICROSECONDS,
                new LinkedBlockingQueue<>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        System.out.println("thread count : " + (++count));
                        return THREAD_FACTORY.newThread(r);
                    }
                });
        executorService.allowCoreThreadTimeOut(true);
        for (int i = 0; i < 20; i++) {
            executorService.submit(new MyClass(i));
        }

        Thread.sleep(2000);

        THREAD_LOCAL_MAP.set("hello world changed");

        System.out.println("active count " + executorService.getPoolSize());
        for (int i = 21; i < 40; i++) {
            executorService.submit(new MyClass(i));
        }
    }

    private static class MyClass implements Callable<String> {

        private final int num;

        MyClass(int num) {
            this.num = num;
        }

        @Override
        public String call() throws Exception {
            System.out.println(THREAD_LOCAL_MAP.get());
            Thread.sleep(1000);
            return String.valueOf(num);
        }
    }
}
