package app.site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author steve
 */
@Service
public class TokenService {
    private static final int TOKEN_TIME_OUT = 1;
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    String create(String adminId) {
        String token = UUID.randomUUID().toString();
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.append(token, adminId);
        redisTemplate.expire(token, TOKEN_TIME_OUT, TimeUnit.HOURS);

        // start transaction
        redisTemplate.multi();
        // todo transaction impl
        redisTemplate.exec();
        // end transaction
        return token;
    }

    boolean isValid(String token) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String s = valueOperations.get(token);
        return s == null;
    }
}
