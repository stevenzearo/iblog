package app.user.api.user;

import org.springframework.lang.NonNull;

/**
 * @author steve
 */
public class RegisterUserRequest {
    public String name;

    public Integer age;

    @NonNull
    public String email;

    @NonNull
    public String password;

    @NonNull
    public String requestedBy;
}
