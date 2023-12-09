package person.liudong.dubbo.client;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;
import person.liudong.dubbo.service.api.GreetingService;

public class ApiClient {
    public static void main(String[] args) {
        ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("first-dubbo-client"));

        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));

        referenceConfig.setInterface(GreetingService.class);
        referenceConfig.setTimeout(5000);

        GreetingService greetingService = referenceConfig.get();
        RpcContext.getClientAttachment().setAttachment("company", "liudong");
        long start = System.currentTimeMillis();
        System.out.println(greetingService.sayHello("world"));
        System.out.println("total cost: " + (System.currentTimeMillis() - start));
    }
}
