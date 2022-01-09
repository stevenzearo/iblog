package app.user.dao;

import app.user.domain.AdminActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminActivityRepository extends JpaRepository<AdminActivity, String> {
    @Override
    AdminActivity save(AdminActivity entity);
}
