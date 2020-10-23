package app.site.cache;

import app.validation.annotation.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * @author steve
 */
@RedisHash(value = "messages", timeToLive = 3 * 60 * 60)
public class ChatGroup implements Serializable {
    @Id
    public String id;

    @NotNull
    @NotBlank
    public String name;

    @NotNull
    public Long createdUserId;

    @NotNull
    public ZonedDateTime createdTime;

    @Override
    public String toString() {
        return "ChatGroup{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", createdUserId='" + createdUserId + '\'' +
            ", createdTime=" + createdTime +
            '}';
    }
}
