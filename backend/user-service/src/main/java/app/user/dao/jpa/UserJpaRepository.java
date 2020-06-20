package app.user.dao.jpa;

import app.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author steve
 */
@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer> {
    User getById(Long id);
}
