package app.site.service;

import app.site.api.user.RegisterUserAJAXRequest;
import app.site.cache.UserCache;
import app.site.cache.User;
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

    public void register(RegisterUserAJAXRequest request) throws WebException {
        RegisterUserRequest registerRequest = new RegisterUserRequest();
        registerRequest.name = request.name;
        registerRequest.age = request.age;
        registerRequest.email = request.email;
        registerRequest.password = request.password;
        registerRequest.requestedBy = REQUESTED_BY;
        EmptyResponse response = userWebService.register(registerRequest);
        ResponseHelper.checkStatusCode(response);
    }

    public User getCurrent(String auth) throws ConflictException {
        Long userId = authService.getAuthedUserId(auth);
        if (userId == null) return null;
        return get(userId);
    }

    public User get(Long userId) {
        return userCache.findById(userId).orElseGet(() -> {
            GetUserResponse data = userWebService.get(userId).data;
            User user = buildUserCache(data);
            userCache.save(user);
            return user;
        });
    }

    public User login(String email, String password, String auth) throws WebException {
        Optional<User> userOptional = userCache.findByEmail(email);
        if (userOptional.isPresent()) {
            throw new ConflictException(ErrorCodes.USER_ALREADY_LOGIN, "user already login, no need login again");
        }


        Response<GetUserByEmailResponse> getUserByEmailResponse = userWebService.getByEmail(email);
        GetUserByEmailResponse data = ResponseHelper.fetchDataWithException(getUserByEmailResponse);
        String encryptedPassword = getEncryptedPassword(password, data);
        if (!encryptedPassword.equals(data.password))
            throw new ConflictException(ErrorCodes.LOGIN_FAILED, "login failed, please your email and password.");
        User user = buildUserCache(data);
        userCache.save(user);
        authService.authUser(auth, user.id);
        return user;
    }

    public void logout(String auth) throws WebException {
        Long userId = authService.getAuthedUserId(auth);
        authService.invalid(auth);
        userCache.deleteById(userId);
    }

    private String getEncryptedPassword(String password, GetUserByEmailResponse user) throws WebException {
        String encryptPassword;
        try {
            encryptPassword = PasswordEncryptHelper.encryptPassword(password, user.salt, user.iteratedTimes);
        } catch (PasswordEncryptException e) {
            throw new WebException(e.getMessage());
        }
        return encryptPassword;
    }

    private User buildUserCache(GetUserByEmailResponse boResponse) {
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
}
