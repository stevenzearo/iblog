package app.site.web.session;

import app.user.AuthorityView;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.ArrayList;
import java.util.List;

/**
 * @author steve
 */
@RedisHash(value = "admin", timeToLive = 60 * 60)
public class Admin {
    @Id
    public String id;
    public String name;
    @Indexed
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

    public static class Group {
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
