package app;

import app.user.api.UserWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steve
 */
@RestController
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableCaching
@EnableRedisRepositories
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Autowired(required = false)
    UserWebService userWebService;

    // todo this is for test, will be removed
    @RequestMapping(value = "/", method = RequestMethod.GET)
    String home() {
        return "Hello, world!";
    }
}
