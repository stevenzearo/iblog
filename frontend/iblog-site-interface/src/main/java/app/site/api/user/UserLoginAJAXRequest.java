package app.site.api.user;

import app.validation.annotation.NotBlank;
import app.validation.annotation.NotNull;
import app.validation.annotation.Size;

/**
 * @author steve
 */
public class UserLoginAJAXRequest {
    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    public String email;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 200)
    public String password;
}
