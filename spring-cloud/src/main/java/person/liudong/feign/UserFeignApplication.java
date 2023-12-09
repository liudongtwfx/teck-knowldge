package person.liudong.feign;

import jakarta.annotation.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import person.liudong.Result;

import java.util.HashMap;
import java.util.Map;

@EnableFeignClients(basePackages = "person.liudong.feign")
@SpringBootApplication
public class UserFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserFeignApplication.class);
    }

    @FeignClient(value = "seckill-provider", path = "/api/demo")
    public interface DemoClient {
        @GetMapping("/hello/v1")
        Result<String> hello();

        @PostMapping("/echo/{word}/v1")
        Result<String> echo(@PathVariable(value = "word") String word);
    }

    @RestController
    @RequestMapping("/api/demo")
    public class DemoController {
        @Resource
        private DemoClient demoClient;

        @GetMapping("/say/hello/v1")
        public Result<Map<String, Object>> hello() {
            Result<String> result = demoClient.hello();
            Map<String, Object> data = new HashMap<>();
            data.put("remote", result);
            return Result.success(data).setMsg("操作成功");
        }
    }
}
