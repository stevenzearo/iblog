package app.site.web.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author steve
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorizedId = request.getHeader("authorizedId");
        if (authorizedId == null || !request.getRequestURI().equals("/authentication")) return false;
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String adminId = valueOperations.get(authorizedId);
        if (adminId == null) {

        }
        return false;
    }
}
