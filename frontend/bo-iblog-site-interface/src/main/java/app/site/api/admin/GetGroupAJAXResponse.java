package app.site.api.admin;

import app.view.user.AuthorityView;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author steve
 */
public class GetGroupAJAXResponse {
    @NonNull
    public String id;

    @NonNull
    public String name;

    @NonNull
    public List<Role> roles = new ArrayList<>();

    public static class Role {
        public String name;

        public AuthorityView authority;
    }
}
