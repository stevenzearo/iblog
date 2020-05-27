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
    BlogDAO blogDAO;

    @Override
    public GetBlogResponse get(String id) {
        logger.info("get blog, id = {}", id);
        Blog blog = blogDAO.get(id);
        return buildGetBlogResponse(blog);
    }

    private GetBlogResponse buildGetBlogResponse(Blog blog) {
        GetBlogResponse response = new GetBlogResponse();
        response.id = blog.id;
        response.userId = blog.userId;
        response.content = blog.content;
        response.tag = blog.tag;
        response.stars = blog.stars;
        response.unStars = blog.unStars;
        response.comments = blog.comments.stream().map(this::buildCommentView).collect(Collectors.toList());
        response.createdTime = blog.createdTime;
        response.createdBy = blog.createdBy;
        response.updatedTime = blog.updatedTime;
        response.updatedBy = blog.updatedBy;
        return response;
    }

    private GetBlogResponse.Comment buildCommentView(Comment comment) {
        GetBlogResponse.Comment commentView = new GetBlogResponse.Comment();
        commentView.id = comment.id;
        commentView.userId = comment.userId;
        commentView.content = comment.content;
        commentView.stars = comment.stars;
        commentView.unStars = comment.unStars;
        commentView.createdTime = comment.createdTime;
        commentView.createdBy = comment.createdBy;
        commentView.updatedTime = comment.updatedTime;
        commentView.updatedBy = comment.updatedBy;
        return commentView;
    }
}
