package app.site.service;

import app.site.web.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author steve
 */
@Service
public class AuthenticationService {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public String authentic() {
        ListOperations<String, String> stringListOperations = redisTemplate.opsForList();
        String auth = UUID.randomUUID().toString();
        stringListOperations.rightPush(Context.AUTHENTICATIONS, auth);
        redisTemplate.expire(Context.AUTHENTICATIONS, Context.AUTH_MINUTES, TimeUnit.MINUTES);
        return auth;
    }
}
