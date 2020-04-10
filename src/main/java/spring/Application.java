package spring;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = {"spring"})
@Slf4j
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            log.info("{}", beanDefinitionName);
        }
    }
}
