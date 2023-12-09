package person.liudong.dubbo.service;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.config.ConfigCenterConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import person.liudong.dubbo.service.api.GreetingServiceAsync;
import person.liudong.dubbo.service.impl.GreetingServiceImpl;

import java.io.IOException;
import java.util.Collections;

public class ApiProvider {
    public static void main(String[] args) {
        ServiceConfig<GreetingServiceAsync> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(GreetingServiceAsync.class);
        serviceConfig.setRef(new GreetingServiceImpl());
        serviceConfig.setConnections(10);
        serviceConfig.setActives(1);

        ConfigCenterConfig configCenterConfig = new ConfigCenterConfig(DubboBootstrap.getInstance().getApplicationModel());
        configCenterConfig.updateParameters(Collections.singletonMap(CommonConstants.THREADS_KEY, "1"));

        DubboBootstrap.getInstance()
                .application("first-dubbo-provider")
                .registry(new RegistryConfig("zookeeper://127.0.0.1:2181"))
                .protocol(new ProtocolConfig("dubbo", -1))
                .service(serviceConfig)
                .configCenter(configCenterConfig)
                .start().await();
    }
}
