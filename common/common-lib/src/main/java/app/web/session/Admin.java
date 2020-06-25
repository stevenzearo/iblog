package app.web.session;

import app.user.AuthorityView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author steve
 */
public class Admin {
    public String id;
    public String name;
    public String email;
    public Group group;

    public static class Group {
        public String id;
        public String name;
        public List<Role> roles = new ArrayList<>();
    }

    public static class Role {
        public String name;
        public AuthorityView authority;
    }
}
