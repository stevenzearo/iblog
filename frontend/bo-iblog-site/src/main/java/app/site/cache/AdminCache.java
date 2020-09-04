package app.site.cache;

import app.site.web.session.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author steve
 */

@Repository
public interface AdminCache extends CrudRepository<Admin, String> {
    Optional<Admin> findByEmail(String email);
}
