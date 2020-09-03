package app.site.cache;

import app.site.web.session.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author steve
 */

@Repository
public interface AdminRedisRepository extends CrudRepository<Admin, String> {
}
