package app.site.cache;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author steve
 */
@Repository
public interface ChatGroupCache extends CrudRepository<ChatGroup, String> {
    List<ChatGroup> findAll();
}
