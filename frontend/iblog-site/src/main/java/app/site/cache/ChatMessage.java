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
@RedisHash(value = "user")
public class ChatMessage implements Serializable {
    @Id
    public String id;

    @Indexed
    public String groupId;

    public ChatMember from;

    public ChatMember to;

    public byte[] content;

    public ZonedDateTime createdTime;

    public static class ChatMember {
        public Long userId;

        public String name;
    }

    @Override
    public String toString() {
        return "ChatMessageCache{" +
            "id='" + id + '\'' +
            ", groupId='" + groupId + '\'' +
            ", from=" + from +
            ", to=" + to +
            ", content=" + Arrays.toString(content) +
            ", createdTime=" + createdTime +
            '}';
    }
}
