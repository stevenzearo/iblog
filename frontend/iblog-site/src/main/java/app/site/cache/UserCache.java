package app.site.cache;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author steve
 */

@Repository
public interface UserCache extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findAllByIdIn(List<Long> ids);
}
