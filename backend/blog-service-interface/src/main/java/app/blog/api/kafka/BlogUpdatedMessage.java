package app.blog.api.kafka;

/**
 * @author steve
 */
public class BlogUpdatedMessage {
    public Integer userId;

    public String blogId;

    public enum Action {
        CREATED,
        STAR,
        DELETE
    }
}
