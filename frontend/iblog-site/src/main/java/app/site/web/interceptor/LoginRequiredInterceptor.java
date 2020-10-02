package app.site.web.interceptor;

import app.site.service.AuthService;
import app.site.web.Context;
import app.site.web.ErrorCodes;
import app.site.web.RequestMethod;
import app.web.error.ConflictException;
import app.web.error.WebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author steve
 */
@Component
public class LoginRequiredInterceptor implements HandlerInterceptor {
    @Autowired
    AuthInterceptor authInterceptor;
    @Autowired
    AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (RequestMethod.OPTIONS.equals(request.getMethod())) return false;
        authInterceptor.preHandle(request, response, handler);
        if (!(handler instanceof HandlerMethod)) {
            throw new WebException(String.format("unknown handler type, type=%s", handler.getClass().getName()));
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LoginRequired loginRequiredInClass = handlerMethod.getBeanType().getAnnotation(LoginRequired.class);
        LoginRequired loginRequiredInMethod = handlerMethod.getMethod().getAnnotation(LoginRequired.class);
        if (loginRequiredInClass == null && loginRequiredInMethod == null) return true;

        String authId = request.getHeader(Context.AUTH_ID);
        Long userId = authService.getAuthedUserId(authId);
        if (userId == null) {
            throw new ConflictException(ErrorCodes.LOGIN_REQUIRED, "login required, please login first");
        }
        return true;
    }
}
