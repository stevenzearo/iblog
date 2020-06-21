package app.user.api.admin;

import org.springframework.lang.NonNull;

/**
 * @author steve
 */
public class BOCreateAdminRequest {
    public String groupId;

    @NonNull
    public String name;

    @NonNull
    public String email;

    @NonNull
    public String password;

    public String requestedBy;

}
