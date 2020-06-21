package app.user.api.admin;

import org.springframework.lang.NonNull;

/**
 * @author steve
 */
public class BOGetAdminByEmailResponse {
    @NonNull
    public String id;
    @NonNull
    public String groupId;
    @NonNull
    public String name;
    @NonNull
    public String email;
    @NonNull
    public String password;
    @NonNull
    public String salt;
    @NonNull
    public String iteratedTime;
}
