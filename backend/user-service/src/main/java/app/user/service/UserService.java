package app.user.service;

import app.user.ErrorCodes;
import app.user.api.user.GetUserByEmailResponse;
import app.user.api.user.GetUserResponse;
import app.user.api.user.RegisterUserRequest;
import app.user.dao.UserRepository;
import app.user.domain.User;
import app.util.PasswordEncryptHelper;
import app.web.error.ConflictException;
import app.web.error.WebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

/**
 * @author steve
 */
@Service
public class UserService {
    private static final int MAX_ITERATED_TIME = 10;

    @Autowired
    UserRepository userJpaRepository;

    public Long register(RegisterUserRequest request) throws WebException {
        User existedUser = userJpaRepository.getFirstByEmail(request.email);
        if (existedUser != null) {
            throw new ConflictException(ErrorCodes.EMAIL_EXIST, "email has been registered.");
        }

        int byteVal = (int) Math.round(Math.random() * (int) Byte.MAX_VALUE) + 1; // 1 ~ 127
        char[] saltChar = Character.toChars(byteVal);
        String salt = new String(saltChar);
        int iteratedTimes = (int) (Math.random() * MAX_ITERATED_TIME) + 1;
        String encryptedPassword = PasswordEncryptHelper.encryptPassword(request.password, salt, iteratedTimes);
        User user = buildUser(request, salt, iteratedTimes, encryptedPassword);
        userJpaRepository.save(user);
        return user.id;
    }

    public GetUserResponse get(Long id) {
        User user = userJpaRepository.getById(id);
        GetUserResponse response = new GetUserResponse();
        response.id = user.id;
        response.name = user.name;
        response.age = user.age;
        response.email = user.email;
        return response;
    }

    public GetUserByEmailResponse getByEmail(String email) {
        User user = userJpaRepository.getFirstByEmail(email);
        if (user == null) return new GetUserByEmailResponse();
        return buildGetUserByEmailResponse(user);
    }

    private GetUserByEmailResponse buildGetUserByEmailResponse(User user) {
        GetUserByEmailResponse.User userView = new GetUserByEmailResponse.User();
        userView.id = user.id;
        userView.email = user.email;
        userView.name = user.name;
        userView.age = user.age;
        userView.password = user.password;
        userView.salt = user.salt;
        userView.iteratedTimes = user.iteratedTimes;
        GetUserByEmailResponse response = new GetUserByEmailResponse();
        response.user = userView;
        return response;
    }

    private User buildUser(RegisterUserRequest request, String salt, int iteratedTimes, String encryptedPassword) {
        User user = new User();
        user.name = request.name;
        user.age = request.age;
        user.email = request.email;
        user.password = encryptedPassword;
        user.salt = salt;
        user.iteratedTimes = iteratedTimes;
        user.createBy = request.requestedBy;
        user.createdTime = ZonedDateTime.now();
        return user;
    }
}
