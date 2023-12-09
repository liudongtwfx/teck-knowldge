package person.liudong.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("spring.application.name", "config-server");
        properties.put("spring.profiles.active", "native");
        properties.put("server.port", 8888);
        properties.put("spring.cloud.config.server.native.searchLocations", "classpath:config/");
        new SpringApplicationBuilder(ConfigServerApplication.class)
                .properties(properties).run(args);
    }

    @RestController
    @RequestMapping("/query")
    public class QueryController {
        @Autowired
        private EurekaClientConfigBean eurekaClientConfigBean;

        @GetMapping("/eureka-server")
        public Object getEurekaServerUrl() {
            return eurekaClientConfigBean.getServiceUrl();
        }
    }
}
