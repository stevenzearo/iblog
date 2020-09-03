package app.site.web.session;

import app.user.AuthorityView;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;

/**
 * @author steve
 */
@RedisHash("admin")
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
