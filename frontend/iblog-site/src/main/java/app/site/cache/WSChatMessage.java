package app.site.cache;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Arrays;

/**
 * @author steve
 */
@RedisHash(value = "messages")
public class WSChatMessage implements Serializable {
    @Id
    public String id;

    @Indexed
    public String groupId;

    @Indexed
    public WSChatMessageType type;

    public WSChatContentMessage chatContentMessage;

    public WSUserJoinMessage userJoinMessage;

    public ZonedDateTime createdTime;

    public static class ChatMember {
        public Long userId;

        public String name;
    }

    @Override
    public String toString() {
        return "WSChatMessage{" +
            "id='" + id + '\'' +
            ", groupId='" + groupId + '\'' +
            ", type=" + type +
            ", chatContentMessage=" + chatContentMessage +
            ", userJoinMessage=" + userJoinMessage +
            ", createdTime=" + createdTime +
            '}';
    }
}
