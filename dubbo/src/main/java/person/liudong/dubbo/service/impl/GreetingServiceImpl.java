package person.liudong.dubbo.service.impl;

import com.alibaba.fastjson2.JSON;
import org.apache.dubbo.rpc.AsyncContext;
import org.apache.dubbo.rpc.RpcContext;
import person.liudong.dubbo.PoJo;
import person.liudong.dubbo.Result;
import person.liudong.dubbo.service.api.GreetingServiceAsync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GreetingServiceImpl implements GreetingServiceAsync {

    private static final ExecutorService THREAD_POOL = new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    @Override
    public final String sayHello(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello " + name + RpcContext.getClientAttachment().getAttachment("company");
    }

    @Override
    public final Result<String> testGeneric(PoJo poJo) {
        Result<String> result = new Result<>();
        result.setSuccess(true);
        try {
            result.setData(JSON.toJSONString(poJo));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public final String asyncSayHello(String name) {
        System.out.println(Thread.currentThread().getName() + ":" + name);
        final AsyncContext asyncContext = RpcContext.startAsync();
        THREAD_POOL.submit(() -> {
            asyncContext.signalContextSwitch();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            asyncContext.write("hello " + name + ", response from provider.");
        });
        return null;
    }
}
