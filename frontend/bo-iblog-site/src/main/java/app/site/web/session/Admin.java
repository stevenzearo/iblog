package app.site.web.session;

import app.user.AuthorityView;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author steve
 */
@RedisHash(value = "admin", timeToLive = 60*60) // TTL = 1h
public class Admin {
    @Id
    public String id;
    public String name;
    public String email;
    public Group group;

    @Override
    public String toString() {
        return "Admin{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", group=" + group +
            '}';
    }

    @RedisHash("group")
    public static class Group {
        @Id
        public String id;
        public String name;
        public List<Role> roles = new ArrayList<>();

        @Override
        public String toString() {
            return "Group{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", roles=" + roles +
                '}';
        }
    }

    @RedisHash("role")
    public static class Role {
        public String name;
        public AuthorityView authority;

        @Override
        public String toString() {
            return "Role{" +
                "name='" + name + '\'' +
                ", authority=" + authority +
                '}';
        }
    }
}
