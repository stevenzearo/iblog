package app.blog.service;

import app.blog.api.blog.GetBlogResponse;
import app.blog.dao.BlogDAO;
import app.blog.domain.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author steve
 */
@Service
public class BlogService {
    @Autowired
    BlogDAO blogDAO;

    public GetBlogResponse get(String id) {
        Blog blog = blogDAO.get(id);
        new GetBlogResponse();
        return null;
    }
}
