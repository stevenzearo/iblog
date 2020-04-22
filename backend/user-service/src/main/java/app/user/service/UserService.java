package app.user.service;

import app.user.api.user.GetUserResponse;
import app.user.dao.jpa.UserJpaDao;
import app.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author steve
 */
@Service
public class UserService {
    @Autowired
    UserJpaDao userJpaDao;

    public GetUserResponse get(Long id) {
        User user = userJpaDao.getById(id);
        GetUserResponse response = new GetUserResponse();
        response.id = user.id;
        response.name = user.name;
        response.age = user.age;
        response.email = user.email;
        return response;
    }
}
