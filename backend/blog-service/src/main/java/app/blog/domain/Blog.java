package app.blog.domain;

import org.bson.types.ObjectId;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author steve
 */
public class Blog {
    public ObjectId id;
    public Long userId;
    public byte[] content;
    public List<String> tag;
    public Integer stars;
    public Integer unStars;
    public List<Comment> comments;
    public ZonedDateTime createdTime;
    public String createdBy;
    public ZonedDateTime updatedTime;
    public String updatedBy;
}
