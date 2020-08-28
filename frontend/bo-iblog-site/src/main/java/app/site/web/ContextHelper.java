package app.site.web;

import app.site.web.session.Admin;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * @author steve
 */
public class ContextHelper {

    /*
     * Design summary of admin in context
     * save admin info as a map structure (id -> admin) in cache which use redis to save cache
     * */
    public static Optional<Admin> getAdmin(HttpSession session) {
        Admin admin = (Admin) session.getAttribute(Context.CURRENT_ADMIN);
        if (admin == null) return Optional.empty();
        return Optional.of(admin);
    }
}
