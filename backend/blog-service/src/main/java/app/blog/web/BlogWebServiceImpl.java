package app.blog.web;

import app.blog.api.BlogWebService;
import app.blog.api.blog.CreateBlogRequest;
import app.blog.api.blog.GetBlogResponse;
import app.blog.api.blog.SearchBlogRequest;
import app.blog.api.blog.SearchBlogResponse;
import app.blog.service.BlogService;
import app.web.response.Response;
import app.web.response.ResponseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author steve
 */
@Component
public class BlogWebServiceImpl implements BlogWebService {
    private final Logger logger = LoggerFactory.getLogger(BlogWebServiceImpl.class);
    @Autowired
    BlogService blogService;

    @Override
    public Response<GetBlogResponse> get(String id) {
        logger.info("get blog, id = {}", id);
        return ResponseHelper.okOf(blogService.get(id));
    }

    @Override
    public Response<String> create(CreateBlogRequest request) {
        String id = blogService.create(request);
        logger.info("created blog, id = {}", id);
        return ResponseHelper.okOf(id);
    }

    @Override
    public Response<SearchBlogResponse> search(SearchBlogRequest request) {
        return ResponseHelper.okOf(blogService.search(request));
    }
}
