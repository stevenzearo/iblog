package app.site.service;

import app.site.cache.RedisTransaction;
import app.site.web.Context;
import app.site.web.ErrorCodes;
import app.web.error.ConflictException;
import app.web.error.WebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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

    public String createAuth() throws WebException {
        String auth = UUID.randomUUID().toString();

        SetOperations<String, String> opsForSet = redisTemplate.opsForSet();
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();

        opsForSet.add(Context.AUTH_SET, auth);
        opsForHash.put(Context.AUTH_MAP, auth, "");
        redisTemplate.expire(auth, Context.AUTH_MINUTES, TimeUnit.MINUTES);
        return auth;
    }

    public String getAuth(HttpServletRequest request) throws WebException {
        String authId = request.getHeader(Context.AUTH_ID);
        if (authId == null || authId.isBlank()) {
            throw new ConflictException(ErrorCodes.AUTH_INVALID, "auth is null or blank, please get and set auth first.");
        }
        return authId;
    }

    public void authUser(String auth, Long userId) throws ConflictException {
        if (isExpired(auth)) {
            throw new ConflictException(ErrorCodes.AUTH_EXPIRED, String.format("auth expired, please get a new auth, auth=%s", auth));
        }
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        opsForHash.put(Context.AUTH_MAP, auth, String.valueOf(userId));
    }

    public Long getAuthedUserId(String auth) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String idStr = opsForHash.get(Context.AUTH_MAP, auth);
        return idStr == null || idStr.isBlank() ? null : Long.valueOf(idStr);
    }

    public void renew(String auth, Long userId) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(Context.AUTH_MAP, auth, String.valueOf(userId));
        redisTemplate.expire(auth, Context.AUTH_MINUTES, TimeUnit.MINUTES);
    }

    public void invalid(String auth) throws WebException {
        if (!isValid(auth)) return;

        SetOperations<String, String> opsForSet = redisTemplate.opsForSet();
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();

        opsForSet.remove(Context.AUTH_SET, auth);
        opsForHash.delete(Context.AUTH_MAP, auth);
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
