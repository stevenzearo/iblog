package app.site.cache;

import app.site.session.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author steve
 */

@Repository
public interface UserCache extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
