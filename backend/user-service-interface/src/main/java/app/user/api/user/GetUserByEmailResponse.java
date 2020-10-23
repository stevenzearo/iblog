package app.user.api.user;

import app.validation.annotation.NotNull;

/**
 * @author steve
 */
public class GetUserByEmailResponse {
    public User user;

    public static class User {
        @NotNull
        public Long id;

        public String name;

        public Integer age;

        @NotNull
        public String email;

        @NotNull
        public String password;

        @NotNull
        public String salt;

        @NotNull
        public Integer iteratedTimes;

    }
}
