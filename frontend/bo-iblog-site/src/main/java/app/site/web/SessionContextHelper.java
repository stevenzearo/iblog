package app.site.web;

import app.web.session.Admin;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * @author steve
 */
public class SessionContextHelper {
    public static Optional<Admin> getAdmin(HttpSession session) {
        Admin admin = (Admin) session.getAttribute(SessionContext.CURRENT_ADMIN);
        if (admin == null) return Optional.empty();
        return Optional.of(admin);
    }
}
