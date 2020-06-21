package app.user.dao;

import app.user.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author steve
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    Admin save(Admin admin);

    Admin getFirstByEmail(String email);
}
