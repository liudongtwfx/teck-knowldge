package spring.controller;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.DynamicThreadPool;


@RestController
@Slf4j
public class UserController {

    @Autowired
    private Gson gson;

    @PostMapping("/users")
    public String getUser(@RequestBody Object request) {
        log.info("post mapping {}", request);
        return "hello world";
    }

    @GetMapping("/users")
    public String getUser(HttpServletRequest request) {
        log.info("{}", request);
        return "hello world";
    }

    @GetMapping("/setCore/{core}")
    public String runThread(@PathVariable("core") int core) {
        DynamicThreadPool.setCore(core);
        return "hello world";
    }
}
