package app;

import app.site.cache.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * @author steve
 */
@RestController
@EnableCaching
@EnableTransactionManagement
@EnableRedisRepositories
@EnableWebSocket
@EnableFeignClients
@SpringBootApplication
@ImportAutoConfiguration
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String home() {
        return "Hello, world!";
    }

    @RequestMapping(value = "/u", method = RequestMethod.GET)
    User user() {
        User user = new User();
        user.id = 1L;
        user.name = "steve";
        user.age = 23;
        user.email = "qq@qq.com";
        return user;
    }
}
