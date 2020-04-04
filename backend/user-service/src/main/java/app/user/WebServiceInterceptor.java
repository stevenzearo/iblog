package app.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author steve
 */
@Component
public class WebServiceInterceptor implements HandlerInterceptor {
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(WebServiceInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return processRequest(request, response);
    }

    private boolean processRequest(HttpServletRequest request, HttpServletResponse response) {
        /*String from = request.getHeader("from");
        boolean isAllowed = false;
        if (from != null) {
            isAllowed = AllowedRequestMeta.allowedFrom.contains(from);
        }

        if (!isAllowed) {
            try {
                logger.warn(MarkerFactory.getMarker("FORBIDDEN_REQUEST"), String.format("forbidden request from %s", request.getRequestURL()));
                response.setStatus(403);
                response.getWriter().write("forbidden request");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isAllowed;*/
        return true;
    }
}
