package app.site.controller;

import app.site.service.AuthService;
import app.site.service.UserService;
import app.site.session.User;
import app.site.web.Context;
import app.site.web.interceptor.AuthRequired;
import app.site.web.interceptor.LoginRequired;
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
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    User login(HttpServletRequest request, @RequestParam("email") String email, @RequestParam("password") String password) throws WebException {
        logger.info(String.format("email: %s", email));
        String auth = request.getHeader(Context.AUTH_ID);
        return userService.login(email, password, auth);
    }

    @LoginRequired
    @RequestMapping(value = "/user/logout", method = RequestMethod.GET)
    void logout(HttpServletRequest request) throws ConflictException {
        String auth = request.getHeader(Context.AUTH_ID);
        userService.logout(auth);
    }

    @LoginRequired
    @RequestMapping(value = "/user/current", method = RequestMethod.GET)
    User getCurrent(HttpServletRequest request) throws ConflictException {
        String auth = request.getHeader(Context.AUTH_ID);
        return userService.getCurrent(auth);
    }
}
