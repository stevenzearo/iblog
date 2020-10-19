package app.site.api.chatgroup;

import app.validation.annotation.NotNull;

import java.time.ZonedDateTime;

/**
 * @author steve
 */
public class CreateAndJoinGroupAJAXResponse {
    @NotNull
    public String groupId;

    @NotNull
    public ZonedDateTime createdTime;
}
