import app.IntegrationTest;
import app.site.cache.AdminCache;
import app.site.cache.Admin;
import app.view.user.AuthorityView;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author steve
 */
public class RedisDemo extends IntegrationTest {
    private final Logger logger = LoggerFactory.getLogger(RedisDemo.class);
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    AdminCache adminCache;

    @Ignore
    @Test
    public void test() throws InterruptedException {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.append("hello", "world");
        redisTemplate.expire("hello", 20, TimeUnit.SECONDS);
        Admin admin = new Admin();
        admin.id = "admin-1";
        admin.name = "admin";
        admin.email = "email-1";
        Admin.Group group = new Admin.Group();
        group.id = "group-1";
        group.name = "group";
        Admin.Role role = new Admin.Role();
        role.authority = AuthorityView.ALL;
        role.name = "all authority user";
        Admin.Role role2 = new Admin.Role();
        role2.authority = AuthorityView.BO_SITE;
        role2.name = "bo authority user";
        group.roles = List.of(role, role2);
        admin.group = group;
        adminCache.save(admin);
        Thread.sleep(5000);

        admin.name = "admin-admin";
        adminCache.save(admin); // test for replace
        Optional<Admin> adminOptional = adminCache.findByEmail("email-1");
        adminOptional.ifPresentOrElse(a -> logger.warn(a.toString()), () -> logger.error("admin not found"));
    }
}
