package jl.multithread;

import lombok.Data;

import java.util.concurrent.CompletableFuture;

/**
 * @author liudong17
 */

public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "first")
                .thenCompose(unused -> CompletableFuture.supplyAsync(() -> unused + "second"));
        System.out.println(future.get());
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    @Data
    private static class A {
        private int age;

    }
}
