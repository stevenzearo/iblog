package app.user.api.user;

import org.springframework.stereotype.Component;

/**
 * @author steve
 */
@Component
public class GetUserResponse {
    public Long id;

    public String name;

    public Integer age;

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
