package app.site.api.admin;

import app.user.AuthorityView;
import org.springframework.lang.NonNull;

/**
 * @author steve
 */
public class CreateRoleAJAXRequest {
    @NonNull
    public String name;

    @NonNull
    public AuthorityView authority;
}
