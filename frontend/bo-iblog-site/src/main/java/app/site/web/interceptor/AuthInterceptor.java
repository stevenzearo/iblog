package app.site.web.interceptor;

import app.site.service.AuthService;
import app.site.web.Context;
import app.site.web.ErrorCodes;
import app.web.error.ConflictException;
import app.web.error.WebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
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
        if (!(handler instanceof HandlerMethod)) {
            throw new WebException(String.format("unknown handler type, type=%s",handler.getClass().getName()));
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        AuthRequired authRequiredInClass = handlerMethod.getBeanType().getAnnotation(AuthRequired.class);
        AuthRequired authRequiredInMethod = handlerMethod.getMethod().getAnnotation(AuthRequired.class);
        if (authRequiredInClass == null && authRequiredInMethod == null) return true;

        String authId = request.getHeader(Context.AUTH_ID);
        if (authId == null) {
            throw new ConflictException(ErrorCodes.AUTH_INVALID, "auth is null, please get auth first.");
        }
        if (authService.isExpired(authId)) return false;
        String adminId = authService.getAuthedAdminId(authId);
        authService.renew(authId, adminId);
        return true;
    }
}
