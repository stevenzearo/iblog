import app.Application;
import app.site.cache.AdminRedisRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author steve
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class RedisDemo {
    private final Logger logger = LoggerFactory.getLogger(RedisDemo.class);
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    AdminRedisRepository adminRedisRepository;

    @Test
    public void test() {
        /*ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.append("hello", "world");
        redisTemplate.expire("hello", 20, TimeUnit.SECONDS);
        Admin admin = new Admin();
        admin.id = "admin-1";
        admin.name = "admin";
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
        adminRedisRepository.save(admin);
        Optional<Admin> adminOptional = adminRedisRepository.findById("admin-1");
        adminOptional.ifPresent(a -> logger.info(a.toString()));*/
    }
}
