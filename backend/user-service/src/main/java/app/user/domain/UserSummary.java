package app.user.domain;

import java.time.ZonedDateTime;

/**
 * @author steve
 */
public class UserSummary {
    public String userId;

    public Integer createdBlogCount;

    public Integer staredBlogCount;

    public Integer followingCount;

    public Integer followerCount;

    public ZonedDateTime createdTime;

    public String createdBy;

    public ZonedDateTime updatedTime;

    public String updatedBy;
}
