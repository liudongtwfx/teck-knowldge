package person.liudong.dubbo.client;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;
import person.liudong.dubbo.service.api.GreetingServiceAsync;

import java.util.concurrent.*;

public class ApiAsyncClient {

    private static final ExecutorService THREAD_POOL = new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    public static void main(String[] args) throws InterruptedException {
        asyncWithCallBack();
        new CountDownLatch(1).await();
    }

    private static void asyncCall() throws ExecutionException, InterruptedException {
        // return null
        GreetingServiceAsync greetingService = getService();

        System.out.println(greetingService.sayHello("world"));
        Future<String> future = RpcContext.getServiceContext().getFuture();
        System.out.println(future.get());
    }


    private static void asyncWithCallBack() throws InterruptedException {
        GreetingServiceAsync service = getService();
        for (int i = 0; i < 1000; i++) {
            // return null
            final int index = i;
            THREAD_POOL.submit(() -> {
                System.out.println(service.asyncSayHello("world " + index));
                RpcContext.getServiceContext().getCompletableFuture()
                        .whenComplete((retValue, exception) -> {
                            if (exception == null) {
                                System.out.println(retValue);
                            } else {
                                exception.printStackTrace();
                            }
                        });
            });
            Thread.sleep(10);
        }
    }


    private static GreetingServiceAsync getService() {
        ReferenceConfig<GreetingServiceAsync> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("first-dubbo-client"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        referenceConfig.setInterface(GreetingServiceAsync.class);
        referenceConfig.setAsync(true);
        referenceConfig.setTimeout(2000);
        return referenceConfig.get();
    }
}
