package spring;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Map;

@SpringBootApplication(scanBasePackages = {"spring"})
@Slf4j
@EnableScheduling
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            log.info("{}", beanDefinitionName);
        }
        Map<String, Long> beansOfType = context.getBeansOfType(Long.class);
        beansOfType.forEach((k, v) -> log.info("key:{},value:{}", k, v));
    }

    @Bean
    public Long timestamp() {
        return System.currentTimeMillis();
    }
}
