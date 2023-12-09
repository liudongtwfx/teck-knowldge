package spring;


import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class DynamicThreadPool implements ApplicationRunner {

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(1, 10, 10, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>());

    private static final AtomicLong COUNT = new AtomicLong();

    public static void setCore(int core) {
        THREAD_POOL_EXECUTOR.setCorePoolSize(core);
    }

    private void run() {
        while (true) {
            THREAD_POOL_EXECUTOR.submit(() -> {
                try {
                    System.out.println(COUNT.getAndIncrement());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            });
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        new Thread(this::run).start();
    }
}
