package app.user.api.admin;

import app.user.AuthorityView;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author steve
 */
public class BOGetAdminByIdResponse {
    @NonNull
    public String id;

    @NonNull
    public String name;

    @NonNull
    public String email;

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
