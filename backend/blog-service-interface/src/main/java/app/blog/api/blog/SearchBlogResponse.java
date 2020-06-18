package app.blog.api.blog;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author steve
 */
public class SearchBlogResponse {
    public Long total;
    public List<Blog> blogs;

    public static class Blog {
        public String id;
        public Long userId;
        public byte[] content;
        public List<String> tags;
        public Integer stars;
        public Integer unStars;
        public List<Comment> comments = List.of();
        public ZonedDateTime createdTime;
        public String createdBy;
        public ZonedDateTime updatedTime;
        public String updatedBy;
    }

    public static class Comment {
        public String id;
        public Long userId;
        public byte[] content;
        public Integer stars;
        public Integer unStars;
        public ZonedDateTime createdTime;
        public String createdBy;
        public ZonedDateTime updatedTime;
        public String updatedBy;
    }
}
