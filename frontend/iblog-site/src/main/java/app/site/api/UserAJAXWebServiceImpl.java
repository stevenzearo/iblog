package app.site.api;

import app.site.api.user.GetCurrentUserAJAXResponse;
import app.site.api.user.UserLoginAJAXRequest;
import app.site.api.user.UserLoginAJAXResponse;
import app.site.api.user.UserRegisterAJAXRequest;
import app.site.service.AuthService;
import app.site.service.UserService;
import app.site.web.Context;
import app.site.web.interceptor.AuthRequired;
import app.site.web.interceptor.LoginRequired;
import app.web.error.WebException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author steve
 */
@Component
@AuthRequired
public class UserAJAXWebServiceImpl implements UserAJAXWebService {
    private final Logger logger = LoggerFactory.getLogger(UserAJAXWebServiceImpl.class);
    @Autowired
    AuthService authService;
    @Autowired
    UserService userService;

    @Override
    public void register(UserRegisterAJAXRequest request) throws WebException {
        userService.register(request);
    }

    public UserLoginAJAXResponse login(@RequestHeader(Context.AUTH_ID) String auth, UserLoginAJAXRequest request) throws WebException {
        logger.info(String.format("email: %s", request.email));
        return userService.login(request.email, request.password, auth);
    }

    @LoginRequired
    @RequestMapping(value = "/user/logout", method = RequestMethod.PUT)
    public void logout(@RequestHeader(Context.AUTH_ID) String auth) throws WebException {
        userService.logout(auth);
    }

    @LoginRequired
    @RequestMapping(value = "/user/current", method = RequestMethod.GET)
    public GetCurrentUserAJAXResponse getCurrent(@RequestHeader(Context.AUTH_ID) String auth) {
        return userService.getCurrent(auth);
    }
}
