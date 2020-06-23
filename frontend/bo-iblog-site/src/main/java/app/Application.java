package app;

import app.user.api.UserWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steve
 */
@RestController
@EnableFeignClients
@SpringBootApplication
public class Application {
    @Autowired(required = false)
    UserWebService userWebService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // todo this is for test, will be removed
    @RequestMapping(value = "/", method = RequestMethod.GET)
    String home() {
        return "Hello, world!";
    }
}
