package iblog.user.service;

import iblog.user.dao.jpa.UserJpaDao;
import iblog.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author steve
 */
@Component
public class UserService {
    @Autowired
    UserJpaDao userJpaDao;

    public User get(Integer id) {
        return userJpaDao.getById(id);
    }
}
