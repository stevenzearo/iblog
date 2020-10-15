package app;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author steve
 */
@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "../../../conf/dev/application.yml", classes = Application.class)
@WebAppConfiguration
public class IntegrationTest {
    @Test
    public void testConfig() {
    }
}
