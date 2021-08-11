package jl.multithread;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

@Slf4j
public class MaxWaitDemo {
    public static void main(String[] args) throws Exception {
        Handler handler = new Handler();
        String handle = handler.handle();
        System.out.println(handle);
        log.info("final ans:{}", handle);
        Thread.sleep(3000);
        System.exit(1);
    }


    static class Handler {
        private static final ExecutorService EXECUTOR_SERVICE = new ThreadPoolExecutor(4, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024));

        public String handle() {
            Future<String> submit = EXECUTOR_SERVICE.submit(new Request());
            try {
                return submit.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    static class Request implements Callable<String> {
        private static final ListeningExecutorService EXECUTOR_SERVICE = MoreExecutors.listeningDecorator(
                new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024)));

        public String request() {
            CountDownLatch countDownLatch = new CountDownLatch(3);
            List<String> ans = new ArrayList<>();
            List<Future<String>> futures = new ArrayList<>();
            List<IORequest> ioRequests = new ArrayList<>();
            ioRequests.add(new IORequest("baidu", countDownLatch));
            ioRequests.add(new IORequest("google", countDownLatch));
            ioRequests.add(new IORequest("bing", countDownLatch));
            for (IORequest ioRequest : ioRequests) {
                futures.add(EXECUTOR_SERVICE.submit(ioRequest));
            }
            try {
                boolean zero = countDownLatch.await(1, TimeUnit.SECONDS);
                System.out.println("zero:" + zero);
                for (int i = 0; i < ioRequests.size(); i++) {
                    if (ioRequests.get(i).completed) {
                        ans.add(futures.get(i).get());
                    }
                }
                return String.join("_", ans);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public String call() throws Exception {
            return request();
        }
    }

    static class IORequest implements Callable<String> {
        private final String requestName;
        private final CountDownLatch countDownLatch;
        private boolean completed = false;

        public IORequest(String requestName, CountDownLatch countDownLatch) {
            this.requestName = requestName;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public String call() throws Exception {
            int costTime = new Random().nextInt(3000);
            Thread.sleep(costTime);
            log.info("requestName:{} cost:{}ms", requestName, costTime);
            countDownLatch.countDown();
            completed = true;
            return requestName;
        }
    }
}
