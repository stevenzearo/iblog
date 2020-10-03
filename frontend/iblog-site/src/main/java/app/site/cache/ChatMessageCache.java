package app.site.cache;

import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author steve
 */
public interface ChatMessageCache extends CrudRepository<ChatMessage, String> {
    List<ChatMessage> findAllByGroupId(String groupId);
    void deleteAllByGroupId(String groupId);
}
