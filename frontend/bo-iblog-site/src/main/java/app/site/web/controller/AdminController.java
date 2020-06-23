package app.site.web.controller;

import app.site.web.SessionContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * @author steve
 */
@RestController
public class AdminController {
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    String login(HttpServletRequest request, @RequestParam("email") String email, @RequestParam("password") String password) {
        request.getSession().setAttribute(SessionContext.USER_ID, "user-0001");
        // todo login password confirm
        return email + " -> " + password;
    }

    @RequestMapping(value = "/admin/logout", method = RequestMethod.GET)
    void logout(HttpServletRequest request) {
        request.getSession().setAttribute(SessionContext.USER_ID, null);
    }
}
