package app.user.dao.jpa;

import app.user.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author steve
 */
public interface RoleJpaRepository extends JpaRepository<Role, String> {
}
