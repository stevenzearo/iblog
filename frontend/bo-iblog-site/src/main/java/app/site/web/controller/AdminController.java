package app.site.web.controller;

import app.site.service.AdminService;
import app.site.web.Context;
import app.site.web.ContextHelper;
import app.site.web.ErrorCodes;
import app.site.web.session.Admin;
import app.web.error.ConflictException;
import app.web.error.ForbiddenException;
import app.web.error.WebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


/**
 * @author steve
 */
@RestController
public class AdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    void login(HttpServletRequest request, @RequestParam("email") String email, @RequestParam("password") String password) throws WebException {
        Optional<Admin> admin = ContextHelper.getAdmin(request.getSession());
        if (admin.isPresent()) throw new ConflictException(ErrorCodes.ADMIN_ALREADY_LOGIN, "admin already login!");
        boolean isLogin = adminService.login(email, password, request);
        if (isLogin) return;

        throw new ForbiddenException(ErrorCodes.LOGIN_FAILED, "login failed, please check the email and password!");
    }

    @RequestMapping(value = "/admin/logout", method = RequestMethod.GET)
    void logout(HttpServletRequest request) {
        request.getSession().setAttribute(Context.CURRENT_ADMIN, null);
    }

    @RequestMapping(value = "/admin/current", method = RequestMethod.GET)
    Admin getCurrent(HttpServletRequest request) {
        Optional<Admin> adminOptional = ContextHelper.getAdmin(request.getSession());
        return adminOptional.orElse(null);
    }
}
