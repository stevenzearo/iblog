package app.site.api;

import app.site.api.user.RegisterUserAJAXRequest;
import app.site.service.UserService;
import app.site.web.interceptor.AuthRequired;
import app.web.error.WebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author steve
 */
@Component
@AuthRequired
public class UserAJAXWebServiceImpl implements UserAJAXWebService {
    @Autowired
    UserService userService;

    @Override
    public void register(RegisterUserAJAXRequest request) throws WebException {
        userService.register(request);
    }
}
