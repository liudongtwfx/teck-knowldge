package jl.multithread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;

/**
 * @author liudong17
 */

@Slf4j
public class CompletableFutureDemo {
    private static final ExecutorService EXECUTOR_SERVICE = new ThreadPoolExecutor(20, 30, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024),
            new ThreadFactoryBuilder().setDaemon(true).setNameFormat("default-thread-%d").build());

    static {
        System.out.println("current Thread " + Thread.currentThread().getName());
    }

    public static void main(String[] args) throws Exception {
        Thread.currentThread().setName("main-thread");
        System.out.println("main Thread " + Thread.currentThread().getName());
        CompletableFuture<String> future = CompletableFuture.runAsync(new ThreadA())
                .thenApplyAsync((Function<Void, Void>) a -> {
                    log.info("second");
                    return null;
                }, EXECUTOR_SERVICE)
                .thenCompose(unused -> CompletableFuture.supplyAsync(() -> {
                    log.info("composed");
                    return "final composed";
                }));
        System.out.println(future.get());
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("invoking...");
            interruptWithoutException(1000);
            return "hello";
        });

        System.out.println(cf1.get());

        allDemo();
        EXECUTOR_SERVICE.shutdownNow();
        System.gc();
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    private static void interruptWithoutException(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    private static void allDemo() throws Exception {
        List<CompletableFuture<Integer>> completableFutureList = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            final int constA = i;
            completableFutureList.add(CompletableFuture.supplyAsync(() -> {
                log.info("i:{}", constA);
                interruptWithoutException(10);
                return constA;
            }, EXECUTOR_SERVICE));
        }
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture
                .allOf(completableFutureList.toArray(new CompletableFuture[0]));
        voidCompletableFuture.get(10, TimeUnit.MILLISECONDS);
    }

    @Data
    private static class A {
        private int age;
    }

    private static class ThreadA implements Runnable {
        private static final Logger log = LoggerFactory.getLogger("ThreadA");

        @Override
        @SneakyThrows
        public void run() {
            Thread.sleep(1000);
            log.info("first");
        }
    }

    private static class ThreadB implements Runnable {
        private static final Logger log = LoggerFactory.getLogger("ThreadB");

        @Override
        public void run() {
            log.info("second");
        }
    }
}
