package app.user.api.admin.role;

import org.springframework.lang.NonNull;

/**
 * @author steve
 */
public class BOCreateRoleRequest {
    @NonNull
    public String name;

    @NonNull
    public AuthorityView authority;

    @NonNull
    public String requestedBy;
}
