package app.site.api.user;

import app.validation.annotation.NotNull;

/**
 * @author steve
 */
public class UserAJAXView {
    @NotNull
    public Long id;

    @NotNull
    public String email;

    public String name;

    public Integer age;
}
