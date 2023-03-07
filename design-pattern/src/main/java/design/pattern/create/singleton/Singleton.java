package design.pattern.create.singleton;


import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class Singleton {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = new ThreadPoolExecutor(1000, 2000, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000));
        Set<DoubleCheckLockSingleton> doubleCheckLockSingletons = new HashSet<>();
        List<Future<DoubleCheckLockSingleton>> futures = new ArrayList<>();
        StopWatch stop = new StopWatch("singleton");

        stop.start("multi thread");
        for (int i = 0; i < 1000; i++) {
            Future<DoubleCheckLockSingleton> submit = executor.submit(new Runner());
            futures.add(submit);
        }
        Thread.sleep(1000);
        futures.forEach(future -> {
            try {
                doubleCheckLockSingletons.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        System.out.println(doubleCheckLockSingletons.size());
        stop.stop();
        System.out.println(stop.prettyPrint());
        executor.shutdownNow();
        System.out.println("hello world");
    }

    private static class Runner implements Callable<DoubleCheckLockSingleton> {
        @Override
        public DoubleCheckLockSingleton call() throws Exception {
            return DoubleCheckLockSingleton.getInstance();
        }
    }

    static class DoubleCheckLockSingleton {
        private static volatile DoubleCheckLockSingleton INSTANCE;
        private String name;

        private DoubleCheckLockSingleton() {
        }

        public static DoubleCheckLockSingleton getInstance() {
            if (INSTANCE == null) {
                synchronized (DoubleCheckLockSingleton.class) {
                    if (INSTANCE == null) {
                        INSTANCE = new DoubleCheckLockSingleton();
                    }
                }
            }
            return INSTANCE;
        }
    }
}
