package person.liudong.eureka.server;

import com.netflix.appinfo.InstanceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaServerStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableEurekaServer
@Slf4j
public class EurekaServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaServerApplication.class)
                .properties("spring.profiles.active=eureka_server")
                .run(args);
    }

    @Component
    public static class EurekaStateChangeListener {
        @EventListener
        public void listen(EurekaInstanceCanceledEvent event) {
            log.info("{} \t {} 服务下线", event.getServerId(), event.getAppName());
        }

        @EventListener
        public void listen(EurekaInstanceRegisteredEvent event) {
            InstanceInfo info = event.getInstanceInfo();
            log.info("{}:{} \t {} 服务上线", info.getIPAddr(), info.getPort(), info.getAppName());
        }

        @EventListener
        public void listen(EurekaInstanceRenewedEvent event) {
            log.info("{} \t {} 服务续约", event.getServerId(), event.getAppName());
        }

        @EventListener
        public void listen(EurekaServerStartedEvent event) {
            log.info("Eureka server启动");
        }
    }
}
