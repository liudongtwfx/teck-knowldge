package jl;

import com.google.common.util.concurrent.RateLimiter;

public class StringBuilder {
    public static void main(String[] args) throws Exception {
        RateLimiter rateLimiter = RateLimiter.create(1);
        double acquire = rateLimiter.acquire(1);
        System.out.println("acquire:" + acquire + "s");

        System.out.println("acquire:" + rateLimiter.acquire(1) + "s");
        System.out.println("acquire:" + rateLimiter.acquire(1) + "s");
        System.out.println("acquire:" + rateLimiter.acquire(1) + "s");
        System.out.println("acquire:" + rateLimiter.acquire(1) + "s");
        for (int i = 0; i < 10; i++) {
            System.out.println("acquire:" + rateLimiter.acquire(1) + "s");
        }
    }
}
