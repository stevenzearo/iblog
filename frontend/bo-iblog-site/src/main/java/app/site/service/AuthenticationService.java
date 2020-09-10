package app.site.service;

import app.site.web.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author steve
 */
@Service
public class AuthenticationService {
    @Autowired
    StringRedisTemplate redisTemplate;

    public String authentic() {
        ListOperations<String, String> stringListOperations = redisTemplate.opsForList();
        String auth = UUID.randomUUID().toString();
        stringListOperations.rightPush(Context.AUTHENTICATIONS, auth);
        redisTemplate.expire(Context.AUTHENTICATIONS, Context.AUTH_MINUTES, TimeUnit.MINUTES);
        return auth;
    }

    public boolean isValid(String authentication) {
        SetOperations<String, String> opsForSet = redisTemplate.opsForSet();
        Boolean existence = opsForSet.isMember(Context.AUTHENTICATIONS, authentication);
        return existence == null ? false : existence;
    }

    public void expire(String authentication) {
        SetOperations<String, String> opsForSet = redisTemplate.opsForSet();
        opsForSet.remove(Context.AUTHENTICATIONS, authentication);
    }

    public void expireAll() {
        redisTemplate.delete(Context.AUTHENTICATIONS);
    }
}
