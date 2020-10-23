package app.site.service;

import app.site.api.user.GetCurrentUserAJAXResponse;
import app.site.api.user.UserAJAXView;
import app.site.api.user.UserLoginAJAXResponse;
import app.site.api.user.UserRegisterAJAXRequest;
import app.site.cache.User;
import app.site.cache.UserCache;
import app.site.web.ErrorCodes;
import app.user.api.UserWebService;
import app.user.api.user.GetUserByEmailResponse;
import app.user.api.user.GetUserResponse;
import app.user.api.user.RegisterUserRequest;
import app.util.PasswordEncryptException;
import app.util.PasswordEncryptHelper;
import app.web.error.ConflictException;
import app.web.error.WebException;
import app.web.response.EmptyResponse;
import app.web.response.Response;
import app.web.response.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author steve
 */
@Service
public class UserService {
    private static final String REQUESTED_BY = "bo-iblog-site";
    @Autowired(required = false)
    UserWebService userWebService;
    @Autowired
    UserCache userCache;
    @Autowired
    AuthService authService;

    public void register(UserRegisterAJAXRequest request) throws WebException {
        RegisterUserRequest registerRequest = new RegisterUserRequest();
        registerRequest.name = request.name;
        registerRequest.age = request.age;
        registerRequest.email = request.email;
        registerRequest.password = request.password;
        registerRequest.requestedBy = REQUESTED_BY;
        EmptyResponse response = userWebService.register(registerRequest);
        ResponseHelper.checkStatusCode(response);
    }

    public User getCurrentUser(String auth) {
        Long userId = authService.getAuthedUserId(auth);
        if (userId == null) return null;
        return get(userId);
    }

    public GetCurrentUserAJAXResponse getCurrent(String auth) {
        Long userId = authService.getAuthedUserId(auth);
        if (userId == null) return null;
        GetCurrentUserAJAXResponse response = new GetCurrentUserAJAXResponse();
        response.user = buildUserAJAXView(get(userId));
        return response;
    }

    public User get(Long userId) {
        return userCache.findById(userId).orElseGet(() -> {
            GetUserResponse data = userWebService.get(userId).data;
            User user = buildUserCache(data);
            userCache.save(user);
            return user;
        });
    }

    public UserLoginAJAXResponse login(String email, String password, String auth) throws WebException {
        Optional<User> userOptional = userCache.findByEmail(email);
        if (userOptional.isPresent()) {
            throw new ConflictException(ErrorCodes.USER_ALREADY_LOGIN, "user already login, no need login again");
        }


        Response<GetUserByEmailResponse> getUserByEmailResponse = userWebService.getByEmail(email);
        GetUserByEmailResponse data = ResponseHelper.fetchDataWithException(getUserByEmailResponse);
        GetUserByEmailResponse.User userFromDB = data.user;

        validateLogin(password, userFromDB);
        User user = buildUserCache(userFromDB);
        userCache.save(user);
        authService.authUser(auth, user.id);

        UserLoginAJAXResponse response = new UserLoginAJAXResponse();
        response.user = buildUserAJAXView(user);
        return response;
    }

    public void logout(String auth) throws WebException {
        Long userId = authService.getAuthedUserId(auth);
        authService.invalid(auth);
        userCache.deleteById(userId);
    }

    private String getEncryptedPassword(String password, GetUserByEmailResponse.User user) throws WebException {
        String encryptPassword;
        try {
            encryptPassword = PasswordEncryptHelper.encryptPassword(password, user.salt, user.iteratedTimes);
        } catch (PasswordEncryptException e) {
            throw new WebException(e.getMessage());
        }
        return encryptPassword;
    }

    private User buildUserCache(GetUserByEmailResponse.User boResponse) {
        User user = new User();
        user.id = boResponse.id;
        user.name = boResponse.name;
        user.age = boResponse.age;
        user.email = boResponse.email;
        return user;
    }

    private User buildUserCache(GetUserResponse boResponse) {
        User user = new User();
        user.id = boResponse.id;
        user.name = boResponse.name;
        user.age = boResponse.age;
        user.email = boResponse.email;
        return user;
    }

    private void validateLogin(String password, GetUserByEmailResponse.User userFromDB) throws WebException {
        if (userFromDB == null)
            throw new ConflictException(ErrorCodes.LOGIN_FAILED, "login failed, please check your email and password.");

        String encryptedPassword = getEncryptedPassword(password, userFromDB);
        if (!encryptedPassword.equals(userFromDB.password))
            throw new ConflictException(ErrorCodes.LOGIN_FAILED, "login failed, please check your email and password.");
    }

    private UserAJAXView buildUserAJAXView(User user) {
        UserAJAXView userView = new UserAJAXView();
        userView.id = user.id;
        userView.email = user.email;
        userView.name = user.name;
        userView.age = user.age;
        return userView;
    }
}
