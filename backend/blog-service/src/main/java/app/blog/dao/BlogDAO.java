package app.blog.dao;

import app.blog.domain.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author steve
 */
@Repository
public interface BlogDAO extends MongoRepository<Blog, String> {
    @Query("{'_id': ?}")
    public Blog get(String id);
}
