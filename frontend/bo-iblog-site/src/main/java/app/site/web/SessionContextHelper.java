package app.site.web;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * @author steve
 */
public class SessionContextHelper {
    public static Optional<String> getUserId(HttpSession session) {
        String userId = (String) session.getAttribute(SessionContext.USER_ID);
        if (userId == null) return Optional.empty();
        return Optional.of(userId);
    }
}
