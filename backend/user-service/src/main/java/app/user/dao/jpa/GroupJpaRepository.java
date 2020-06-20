package app.user.dao.jpa;

import app.user.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author steve
 */
public interface GroupJpaRepository extends JpaRepository<Group, String> {
    Group save(Group group);

    List<Group> find();

    Group getById(String id);
}
