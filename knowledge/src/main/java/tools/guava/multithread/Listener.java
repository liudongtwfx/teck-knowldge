package tools.guava.multithread;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Listener {

    private static final ExecutorService THREAD_POOL = new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    public static void main(String[] args) {
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(THREAD_POOL);
        listeningExecutorService.submit(() -> System.out.println("hello"))
                .addListener(() -> System.out.println("world"), THREAD_POOL);
    }
}
