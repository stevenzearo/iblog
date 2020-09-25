package app.site.service;

import app.IntegrationTest;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.Topic;

/**
 * @author steve
 */
@Ignore
class ChatDemo extends IntegrationTest {
    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    public void testChannel() throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            Topic chatTopic = new ChannelTopic("test-channel");
            redisTemplate.convertAndSend(chatTopic.getTopic(), "hello, world");
            Thread.sleep(500);
        }
    }
}