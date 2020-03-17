package iblog.user.dao.jpa;

import iblog.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author steve
 */
@Repository
public interface UserJpaDao extends JpaRepository<User, Integer> {
    User getById(Integer id);
}
