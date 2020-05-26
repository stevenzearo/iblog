package app.blog.domain;

import org.bson.types.ObjectId;

import java.time.ZonedDateTime;

/**
 * @author steve
 */
public class CollectBlog {
    public ObjectId blogId;
    public Long collectedUserId;
    public String collectedUserName;
    public ZonedDateTime collectedTime;
}
