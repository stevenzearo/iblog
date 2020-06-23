package app.site.web.interceptor;

import app.site.web.SessionContextHelper;
import app.site.web.error.UnAuthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @author steve
 */
@Component
public class LoginRequiredInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().startsWith("/admin/login")) return true;
        Optional<String> userIdOptional = SessionContextHelper.getUserId(request.getSession());
        if (userIdOptional.isEmpty()) throw new UnAuthorizedException("unauthorized, please sign in first!!!");
        return true;
    }
}
