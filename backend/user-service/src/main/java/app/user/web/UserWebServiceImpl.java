package app.user.web;

import app.user.api.UserWebService;
import app.user.api.user.GetUserResponse;
import app.user.service.UserService;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author steve
 */
@Component
public class UserWebServiceImpl implements UserWebService {
    private static Logger logger = LoggerFactory.logger(UserWebServiceImpl.class);
    @Autowired
    UserService userService;

    @Override
    public GetUserResponse get(Long id) {
        logger.debug(String.format("id: %d", id));
        return userService.get(id);
    }
}
