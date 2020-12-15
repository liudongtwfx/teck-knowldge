package jl.multithread;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @author liudong17
 */

public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(5) + 5;
            sleep(t * 1000L);
            return String.valueOf(t);
        });
        CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> String.valueOf(new Random().nextInt(100)))
                .applyToEither(f1, t -> t + 1);
        f3.join();
        System.out.println("main end");
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    @Slf4j
    private static class EatRunner implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            System.out.println("eat beginning");
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                log.info("eat interrupted");
                Thread.currentThread().interrupt();
            }
            System.out.println("eat ending");
        }
    }
}
