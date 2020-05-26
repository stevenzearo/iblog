package app.blog.domain;

import org.bson.types.ObjectId;

import java.time.ZonedDateTime;

/**
 * @author steve
 */
public class Comment {
    public ObjectId id;
    public ObjectId blogId;
    public Long userId;
    public byte[] content;
    public Integer stars;
    public Integer unStars;
    public ZonedDateTime createdTime;
    public String createdBy;
    public ZonedDateTime updatedTime;
    public String updatedBy;
}
