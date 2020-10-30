package app.blog.kafka;

import app.blog.IntegrationTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author steve
 */
@Ignore
public class KafkaDemo extends IntegrationTest {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Test
    public void producerTest() {
        kafkaTemplate.send("test-topic", "hello, world! helllllllloooooooo");
    }
}
