package app.site.service;

import app.site.cache.RedisTransaction;
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
    @Autowired
    RedisTransaction redisTransaction;

    public String createAuth() {
        String auth = UUID.randomUUID().toString();

        SetOperations<String, String> opsForSet = redisTemplate.opsForSet();
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        redisTransaction.transaction(() -> {
            opsForSet.add(Context.AUTH_SET, auth);
            opsForHash.put(Context.AUTH_MAP, auth, "");
            redisTemplate.expire(auth, Context.AUTH_MINUTES, TimeUnit.MINUTES);
        });
        return auth;
    }

    public void authAdmin(String auth, String adminId) throws ConflictException {
        if (isExpired(auth)) {
            throw new ConflictException(ErrorCodes.AUTH_EXPIRED, String.format("auth expired, auth = %s", auth));
        }
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        opsForHash.put(Context.AUTH_MAP, auth, adminId);
    }

    public String getAuthedAdminId(String auth) throws ConflictException {
        if (isExpired(auth))
            throw new ConflictException(ErrorCodes.AUTH_EXPIRED, String.format("auth expired, auth = %s", auth));
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(Context.AUTH_MAP, auth);
    }

    public void renew(String auth) throws ConflictException {
        if (!isValid(auth))
            throw new ConflictException(ErrorCodes.AUTH_INVALID, String.format("auth invalid, %s", auth));

        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(Context.AUTH_MAP, auth, null);
        redisTemplate.expire(auth, Context.AUTH_MINUTES, TimeUnit.MINUTES);
    }

    public void invalid(String auth) {
        if (!isValid(auth)) return;

        SetOperations<String, String> opsForSet = redisTemplate.opsForSet();
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();

        redisTransaction.transaction(() -> {
            opsForSet.remove(Context.AUTH_SET, auth);
            opsForHash.delete(Context.AUTH_MAP, auth);
        });
    }

    public boolean isValid(String auth) {
        SetOperations<String, String> opsForSet = redisTemplate.opsForSet();
        Boolean existence = opsForSet.isMember(Context.AUTH_SET, auth);
        return existence == null ? false : existence;
    }

    public boolean isExpired(String auth) throws ConflictException {
        if (!isValid(auth))
            throw new ConflictException(ErrorCodes.AUTH_INVALID, String.format("auth invalid, %s", auth));
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        return !opsForHash.hasKey(Context.AUTH_MAP, auth);
    }

    public void expire(String auth) throws ConflictException {
        if (!isValid(auth))
            throw new ConflictException(ErrorCodes.AUTH_INVALID, String.format("auth invalid, %s", auth));

        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        opsForHash.delete(Context.AUTH_MAP, auth);
    }

    public void invalidAll() {
        redisTemplate.multi();
        redisTemplate.delete(Context.AUTH_MAP);
        redisTemplate.delete(Context.AUTH_SET);
        redisTemplate.exec();
    }
}
