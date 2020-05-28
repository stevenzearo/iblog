package app.blog.service;

import app.blog.api.blog.GetBlogResponse;
import app.blog.domain.Blog;
import app.blog.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * @author steve
 */
@Service
public class BlogService {
    @Autowired
    MongoTemplate mongoTemplate; // cause mongoRepository dose'nt work

    public GetBlogResponse get(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Blog blog = mongoTemplate.findOne(query, Blog.class);
        return buildGetBlogResponse(blog);
    }

    private GetBlogResponse buildGetBlogResponse(Blog blog) {
        GetBlogResponse response = new GetBlogResponse();
        if (blog == null) return response;
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
