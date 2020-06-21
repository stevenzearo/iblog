package app.user.dao;

import app.user.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author steve
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    List<Role> findByGroupId(String groupId);
}
