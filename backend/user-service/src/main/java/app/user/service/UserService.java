package app.user.service;

import app.user.api.user.GetUserResponse;
import app.user.dao.jpa.UserJpaRepository;
import app.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author steve
 */
@Service
public class UserService {
    @Autowired
    UserJpaRepository userJpaRepository;

    public GetUserResponse get(Long id) {
        User user = userJpaRepository.getById(id);
        GetUserResponse response = new GetUserResponse();
        response.id = user.id;
        response.name = user.name;
        response.age = user.age;
        response.email = user.email;
        return response;
    }
}
