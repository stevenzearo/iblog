package app.user.web;

import app.user.api.UserWebService;
import app.user.api.user.GetUserByEmailResponse;
import app.user.api.user.GetUserResponse;
import app.user.api.user.RegisterUserRequest;
import app.user.service.UserService;
import app.web.error.WebErrorCodes;
import app.web.response.EmptyResponse;
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
    private final Logger logger = LoggerFactory.getLogger(UserWebServiceImpl.class);
    @Autowired
    UserService userService;

    @Override
    public EmptyResponse register(RegisterUserRequest request) {
        logger.debug(String.format("registering email: %s", request.email));
        Response<Long> response = ResponseHelper.encloseWithException(() -> userService.register(request));
        if (response.statusCode == WebErrorCodes.OK) {
            logger.debug(String.format("registered user, id = %d", response.data));
        }
        return response;
    }

    @Override
    public Response<GetUserResponse> get(Long id) {
        logger.debug(String.format("id: %d", id));
        return ResponseHelper.okOf(userService.get(id));
    }

    @Override
    public Response<GetUserByEmailResponse> getByEmail(String email) {
        logger.debug(String.format("email: %s", email));
        return ResponseHelper.okOf(userService.getByEmail(email));
    }
}
