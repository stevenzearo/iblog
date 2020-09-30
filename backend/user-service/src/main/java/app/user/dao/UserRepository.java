package app.user.dao;

import app.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author steve
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User getById(Long id);

    User getFirstByEmail(String email);
}
