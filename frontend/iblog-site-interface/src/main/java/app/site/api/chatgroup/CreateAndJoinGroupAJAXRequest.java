package app.site.api.chatgroup;

import app.validation.annotation.NotBlank;
import app.validation.annotation.NotNull;
import app.validation.annotation.Size;


/**
 * @author steve
 */
public class CreateAndJoinGroupAJAXRequest {
    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    public String name;
}
