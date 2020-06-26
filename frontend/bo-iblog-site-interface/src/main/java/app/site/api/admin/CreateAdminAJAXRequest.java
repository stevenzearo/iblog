package app.site.api.admin;

import org.springframework.lang.NonNull;

/**
 * @author steve
 */
public class CreateAdminAJAXRequest {
    @NonNull
    public String name;
    @NonNull
    public String email;
    @NonNull
    public String password;
    @NonNull
    public String groupId;

    @Override
    public String toString() {
        return "CreateAdminAJAXRequest{" +
            "name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", groupId='" + groupId + '\'' +
            '}';
    }
}
