package app.site.api;

import app.site.api.user.GetCurrentUserAJAXResponse;
import app.site.api.user.UserLoginAJAXRequest;
import app.site.api.user.UserLoginAJAXResponse;
import app.site.api.user.UserRegisterAJAXRequest;
import app.web.error.WebException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steve
 */
@RestController
public interface UserAJAXWebService {
    @RequestMapping(value = "/ajax/user", method = RequestMethod.POST)
    void register(@RequestBody UserRegisterAJAXRequest request) throws WebException;

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    UserLoginAJAXResponse login(String auth, @RequestBody UserLoginAJAXRequest request) throws WebException;

    @RequestMapping(value = "/user/logout", method = RequestMethod.PUT)
    void logout(String auth) throws WebException;

    @RequestMapping(value = "/user/current", method = RequestMethod.GET)
    GetCurrentUserAJAXResponse getCurrent(String auth);
}
