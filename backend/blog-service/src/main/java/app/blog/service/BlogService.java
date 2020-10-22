package app.blog.service;

import app.blog.api.blog.CreateBlogRequest;
import app.blog.api.blog.GetBlogResponse;
import app.blog.api.blog.SearchBlogRequest;
import app.blog.api.blog.SearchBlogResponse;
import app.blog.domain.Blog;
import app.blog.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author steve
 */
@Service
public class BlogService {
    @Autowired
    MongoTemplate mongoTemplate; // cause mongoRepository didn't work

    public GetBlogResponse get(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Blog blog = mongoTemplate.findOne(query, Blog.class);
        return buildGetBlogResponse(blog);
    }

    public String create(CreateBlogRequest request) {
        Blog blog = new Blog();
        blog.id = UUID.randomUUID().toString();
        blog.userId = request.userId;
        blog.content = request.content;
        blog.tags = request.tags;
        blog.createdTime = ZonedDateTime.now();
        blog.createdBy = request.createdBy;
        mongoTemplate.insert(blog);
        return blog.id;
    }

    public SearchBlogResponse search(SearchBlogRequest request) {
        Query query = new Query(Criteria.where("tags").in(request.tags)).skip(request.skip).limit(request.limit);
        List<Blog> blogs = mongoTemplate.find(query, Blog.class);
        long total = mongoTemplate.count(query, Blog.class);
        return buildSearchBlogResponse(blogs, total);
    }

    private GetBlogResponse buildGetBlogResponse(Blog blog) {
        GetBlogResponse response = new GetBlogResponse();
        if (blog == null) return response;
        response.id = blog.id;
        response.userId = blog.userId;
        response.content = blog.content;
        response.tag = blog.tags;
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


    private SearchBlogResponse buildSearchBlogResponse(List<Blog> blogs, long total) {
        SearchBlogResponse response = new SearchBlogResponse();
        response.total = total;
        response.blogs = blogs.stream().map(this::buildBlogView).collect(Collectors.toList());
        return response;
    }

    private SearchBlogResponse.Blog buildBlogView(Blog blog) {
        SearchBlogResponse.Blog blogView = new SearchBlogResponse.Blog();
        blogView.id = blog.id;
        blogView.userId = blog.userId;
        blogView.content = blog.content;
        blogView.tags = blog.tags;
        blogView.stars = blog.stars;
        blogView.unStars = blog.unStars;
        blogView.createdTime = blog.createdTime;
        blogView.createdBy = blog.createdBy;
        blogView.updatedTime = blog.updatedTime;
        blogView.updatedBy = blog.updatedBy;
        blogView.comments = blog.comments.stream().map(this::buildSearchCommentView).collect(Collectors.toList());
        return blogView;
    }

    private SearchBlogResponse.Comment buildSearchCommentView(Comment comment) {
        SearchBlogResponse.Comment commentView = new SearchBlogResponse.Comment();
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
