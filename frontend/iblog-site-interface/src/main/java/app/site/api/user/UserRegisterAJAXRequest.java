package app.site.api.user;

import app.validation.annotation.Max;
import app.validation.annotation.Min;
import app.validation.annotation.NotBlank;
import app.validation.annotation.NotNull;
import app.validation.annotation.Size;

/**
 * @author steve
 */
public class UserRegisterAJAXRequest {
    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    public String email;

    @NotNull
    @NotBlank
    public String password;

    @NotBlank
    public String name;

    @Min(0)
    @Max(200)
    public Integer age;
}
