package app.user.api.user;

import org.springframework.lang.NonNull;

/**
 * @author steve
 */
public class GetUserResponse {
    @NonNull
    public Long id;

    public String name;

    public Integer age;

    @NonNull
    public String email;

    @Override
    public String toString() {
        return "User{"
            + "id=" + id
            + ", name='" + name + '\''
            + ", age=" + age
            + ", email='" + email + '\''
            + '}';
    }

}
