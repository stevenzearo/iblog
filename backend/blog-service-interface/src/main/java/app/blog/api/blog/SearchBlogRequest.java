package app.blog.api.blog;

import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * @author steve
 */
public class SearchBlogRequest {
    @NonNull
    public List<String> tags;
    @NonNull
    public Long skip;
    @NonNull
    public Integer limit;
}
