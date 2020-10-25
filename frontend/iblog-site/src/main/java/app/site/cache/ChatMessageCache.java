package app.site.cache;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author steve
 */
public interface ChatMessageCache extends CrudRepository<WSChatMessage, String> {
    List<WSChatMessage> findAllByGroupIdAndType(String groupId, WSChatMessageType type);
    void deleteAllByGroupId(String groupId);
}
