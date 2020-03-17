package iblog.user.web;

import iblog.user.domain.User;
import iblog.user.service.UserService;
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
    public User get(Integer id) {
        return userService.get(id);
    }
}
