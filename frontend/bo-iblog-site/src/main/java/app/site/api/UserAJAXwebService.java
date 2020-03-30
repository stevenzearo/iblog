package app.site.api;

import app.user.api.UserWebService;
import app.user.api.user.GetUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steve
 */
@RestController
public class UserAJAXwebService {
    @Autowired(required = false)
    UserWebService userWebService;

    @RequestMapping("/user/{id}")
    GetUserResponse getUser(@PathVariable("id") Integer id) {
        return userWebService.get(id);
    }
}
