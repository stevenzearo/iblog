package app.user.web;

import app.user.api.UserWebService;
import app.user.api.user.GetUserResponse;
import app.user.domain.User;
import app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author steve
 */
@Component
public class UserWebServiceImpl implements UserWebService {
    @Autowired
    UserService userService;

    @Override
    public GetUserResponse get(Integer id) {
        User user = userService.get(id);
        GetUserResponse response = new GetUserResponse();
        response.id = user.id;
        response.name = user.name;
        response.age = user.age;
        response.email = user.email;
        return response;
    }
}
