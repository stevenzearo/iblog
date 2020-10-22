package app.user.domain;

import java.time.ZonedDateTime;

/**
 * @author steve
 */
public class UserFollow {
    public String id;

    public String userId;

    public String followerId;

    public String followingId;

    public ZonedDateTime createdTime;

    public String createdBy;
}
