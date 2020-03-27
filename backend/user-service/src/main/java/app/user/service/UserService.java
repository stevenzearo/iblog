package app.user.service;

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

    public User get(Integer id) {
        return userJpaDao.getById(id);
    }
}
