package app.user.api.admin;

import app.view.user.AuthorityView;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author steve
 */
public class BOGetAdminByEmailResponse {
    @NonNull
    public String id;

    @NonNull
    public String name;

    @NonNull
    public String email;

    @NonNull
    public String password;

    @NonNull
    public String salt;

    @NonNull
    public Integer iteratedTimes;

    @NonNull
    public Group group;

    public static class Group {
        @NonNull
        public String id;

        @NonNull
        public String name;

        @NonNull
        public List<Role> roles = new ArrayList<>();
    }

    public static class Role {
        @NonNull
        public String name;
        @NonNull
        public AuthorityView authority;
    }
}
