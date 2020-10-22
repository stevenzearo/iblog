package app.blog.domain;

import java.time.ZonedDateTime;

/**
 * @author steve
 */
public class StarRecord {
    public String id;

    public Integer userId;

    public String staredBlogId;

    public String staredBlogTitle;

    public ZonedDateTime createdTime;

    public String createdBy;
}
