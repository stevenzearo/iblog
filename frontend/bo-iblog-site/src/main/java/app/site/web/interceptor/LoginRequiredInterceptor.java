package app.site.web.interceptor;

import app.site.web.ErrorCodes;
import app.site.web.RequestMethod;
import app.site.web.ContextHelper;
import app.web.error.UnAuthorizedException;
import app.site.web.session.Admin;
import org.springframework.stereotype.Component;
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
        if (RequestMethod.OPTIONS.equals(request.getMethod())) return false;
        if (request.getRequestURI().startsWith("/admin/login")) return true;
        Optional<Admin> currentUser = ContextHelper.getAdmin(request.getSession());
        if (currentUser.isEmpty()) throw new UnAuthorizedException(ErrorCodes.UNAUTHORIZED, "unauthorized, please sign in first!!!");
        return true;
    }
}
