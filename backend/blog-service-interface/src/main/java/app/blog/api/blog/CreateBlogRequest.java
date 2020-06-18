package app.blog.api.blog;

import org.springframework.lang.NonNull;

import java.util.List;

/**
 * @author steve
 */
public class CreateBlogRequest {
    @NonNull
    public Long userId;
    @NonNull
    public byte[] content;
    @NonNull
    public List<String> tags;
    @NonNull
    public String createdBy;
}
