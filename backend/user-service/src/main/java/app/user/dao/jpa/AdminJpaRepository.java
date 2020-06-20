package app.user.dao.jpa;

import app.user.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author steve
 */
public interface AdminJpaRepository extends JpaRepository<Admin, String> {
    Admin save(Admin admin);

    Admin getFirstByEmail(String email);
}
