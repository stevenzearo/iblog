package app.user.dao;

import app.user.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author steve
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, String> {
    Group save(Group group);

    List<Group> find();

    Group getById(String id);

    void removeById(String id);
}
