package app.site.cache;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

/**
 * @author steve
 */
@RedisHash(value = "user", timeToLive = 60 * 60) // ttl = 1h
public class User {
    @Id
    public Long id;

    public String name;

    public Integer age;

    @Indexed
    public String email;

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", age=" + age +
            ", email='" + email + '\'' +
            '}';
    }
}
