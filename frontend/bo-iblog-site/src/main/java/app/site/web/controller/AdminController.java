package app.site.web.controller;

import app.site.service.AdminService;
import app.site.web.Context;
import app.site.web.ContextHelper;
import app.site.web.session.Admin;
import app.web.error.WebException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    Admin login(HttpServletRequest request, @RequestParam("email") String email, @RequestParam("password") String password) throws WebException {
        logger.info(String.format("email: %s", email));
        return adminService.login(email, password, request);
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
