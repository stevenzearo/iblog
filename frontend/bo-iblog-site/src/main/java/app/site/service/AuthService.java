package app.site.service;

import app.site.web.Context;
import app.site.web.ErrorCodes;
import app.web.error.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author steve
 */
@Service
public class AuthService {
    @Autowired
    StringRedisTemplate redisTemplate;

    public String auth() {
        String auth = UUID.randomUUID().toString();

        SetOperations<String, String> opsForSet = redisTemplate.opsForSet();
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();

        redisTemplate.multi();
        opsForSet.add(Context.AUTH_SET, auth);
        opsForHash.put(Context.AUTH_MAP, auth, null);
        redisTemplate.expire(auth, Context.AUTH_MINUTES, TimeUnit.MINUTES);
        redisTemplate.exec();
        return auth;
    }

    public void renew(String auth) throws ConflictException {
        if (!isValid(auth))
            throw new ConflictException(ErrorCodes.AUTH_INVALID, String.format("auth invalid, %s", auth));

        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(Context.AUTH_MAP, auth, null);
        redisTemplate.expire(auth, Context.AUTH_MINUTES, TimeUnit.MINUTES);
    }

    public boolean isValid(String auth) {
        SetOperations<String, String> opsForSet = redisTemplate.opsForSet();
        Boolean existence = opsForSet.isMember(Context.AUTH_SET, auth);
        return existence == null ? false : existence;
    }

    public boolean isExpired(String auth) throws ConflictException {
        if (!isValid(auth))
            throw new ConflictException(ErrorCodes.AUTH_INVALID, String.format("auth invalid, %s", auth));
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        return !opsForHash.hasKey(Context.AUTH_MAP, auth);
    }

    public void expire(String auth) {
        if (!isValid(auth)) return;

        SetOperations<String, String> opsForSet = redisTemplate.opsForSet();
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();

        redisTemplate.multi();
        opsForSet.remove(Context.AUTH_SET, auth);
        opsForHash.delete(Context.AUTH_MAP, auth);
        redisTemplate.exec();
    }

    public void expireAll() {
        redisTemplate.multi();
        redisTemplate.delete(Context.AUTH_MAP);
        redisTemplate.delete(Context.AUTH_SET);
        redisTemplate.exec();
    }
}
