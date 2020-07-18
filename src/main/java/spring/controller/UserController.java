package spring.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private Gson gson;

    @GetMapping("/users")
    public String getUser(HttpServletRequest request) {
        log.info("{}", request);
        return "hello world";
    }
}
