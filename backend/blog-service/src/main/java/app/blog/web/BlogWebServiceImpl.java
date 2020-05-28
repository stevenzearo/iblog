package app.blog.web;

import app.blog.api.BlogWebService;
import app.blog.api.blog.GetBlogResponse;
import app.blog.dao.BlogDAO;
import app.blog.domain.Blog;
import app.blog.domain.Comment;
import app.blog.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author steve
 */
@Component
public class BlogWebServiceImpl implements BlogWebService {
    private final Logger logger = LoggerFactory.getLogger(BlogWebServiceImpl.class);
    @Autowired
    BlogService blogService;

    @Override
    public GetBlogResponse get(String id) {
        logger.info("get blog, id = {}", id);
        return blogService.get(id);
    }
}
