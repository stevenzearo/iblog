package app;

import app.user.api.UserWebService;
import app.user.api.user.GetUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steve
 */
@RestController
@SpringBootApplication
public class Application {
    @Autowired
    UserWebService userWebService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // todo this is for test, will be removed
    @RequestMapping("/")
    String home() {
        return "Hello, world!";
    }

    @RequestMapping("/user/{id}")
    GetUserResponse getUser(@PathVariable("id") Integer id) {
        return userWebService.get(id);
    }
}
