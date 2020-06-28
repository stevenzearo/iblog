package app.user.web;

import app.user.api.UserWebService;
import app.user.api.user.GetUserResponse;
import app.user.service.UserService;
import app.web.response.Response;
import app.web.response.ResponseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author steve
 */
@Component
public class UserWebServiceImpl implements UserWebService {
    private static Logger logger = LoggerFactory.getLogger(UserWebServiceImpl.class);
    @Autowired
    UserService userService;

    @Override
    public Response<GetUserResponse> get(Long id) {
        logger.debug(String.format("id: %d", id));
        return ResponseHelper.okOf(userService.get(id));
    }
}
