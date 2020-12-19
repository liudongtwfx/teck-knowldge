package jl.multithread;

import lombok.Data;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @author liudong17
 */

public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(5) + 5;
            sleep(1000L);
            return t;
        });

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(5);
            sleep(1000);
            return t;
        });


        CompletableFuture<Integer> f3 = f1.thenCombine(f2, (a, b) -> {
            System.out.println(a.getClass().getName());
            System.out.println(b.getClass().getName());
            return a + b;
        });

        f3.join();
        System.out.println("main end");

        System.out.println(f1.get());
        System.out.println(f2.get());
        System.out.println(f3.get());
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