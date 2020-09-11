package app.site.web.interceptor;

import app.site.service.AuthService;
import app.site.web.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author steve
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    AuthService authService;
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AuthRequired annotation = handler.getClass().getAnnotation(AuthRequired.class);
        if (annotation == null) return true;
        String authId = request.getHeader(Context.AUTH_ID);
        if (authId == null) return false;
        if (authService.isExpired(authId)) return false;
        authService.renew(authId);
        return true;
    }
}
