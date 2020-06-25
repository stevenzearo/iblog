package app.site.web.controller;

import app.site.service.AdminService;
import app.site.web.ErrorCodes;
import app.site.web.SessionContext;
import app.web.error.ForbiddenException;
import app.web.error.WebException;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired(required = true)
    AdminService adminService;

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    void login(HttpServletRequest request, @RequestParam("email") String email, @RequestParam("password") String password) throws WebException {
        boolean isLogin = adminService.login(email, password, request);
        if (isLogin) return;

        throw new ForbiddenException(ErrorCodes.LOGIN_FAILED, "login failed, please check the email and password!");
    }

    @RequestMapping(value = "/admin/logout", method = RequestMethod.GET)
    void logout(HttpServletRequest request) {
        request.getSession().setAttribute(SessionContext.CURRENT_ADMIN, null);
    }
}
