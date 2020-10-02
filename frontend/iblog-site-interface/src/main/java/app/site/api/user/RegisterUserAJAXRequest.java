package app.site.api.user;

import org.springframework.lang.NonNull;

/**
 * @author steve
 */
public class RegisterUserAJAXRequest {
    public String name;

    public Integer age;

    @NonNull
    public String email;

    @NonNull
    public String password;
}
