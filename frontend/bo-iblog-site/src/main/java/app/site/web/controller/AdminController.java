package app.site.web.controller;

import app.site.service.AdminService;
import app.site.service.AuthService;
import app.site.web.Context;
import app.site.web.interceptor.AuthRequired;
import app.site.web.interceptor.LoginRequired;
import app.site.web.session.Admin;
import app.web.error.ConflictException;
import app.web.error.WebException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * @author steve
 */
@AuthRequired
@RestController
public class AdminController {
    private final Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    AdminService adminService;
    @Autowired
    AuthService authService;

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    Admin login(HttpServletRequest request, @RequestParam("email") String email, @RequestParam("password") String password) throws WebException {
        logger.info(String.format("email: %s", email));
        String auth = request.getHeader(Context.AUTH_ID);
        return adminService.login(email, password, auth);
    }

    @LoginRequired
    @RequestMapping(value = "/admin/logout", method = RequestMethod.GET)
    void logout(HttpServletRequest request) throws ConflictException {
        String auth = request.getHeader(Context.AUTH_ID);
        adminService.logout(auth);
    }

    @LoginRequired
    @RequestMapping(value = "/admin/current", method = RequestMethod.GET)
    Admin getCurrent(HttpServletRequest request) throws ConflictException {
        String auth = request.getHeader(Context.AUTH_ID);
        return adminService.getCurrent(auth);
    }
}
