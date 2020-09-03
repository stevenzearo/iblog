import app.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author steve
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class RedisDemo {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Test
    public void test() {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.append("hello", "world");
        redisTemplate.expire("hello", 20, TimeUnit.SECONDS);
    }
}
