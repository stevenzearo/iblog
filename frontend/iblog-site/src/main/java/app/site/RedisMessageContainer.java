package app.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;

/**
 * @author steve
 */
@Configuration
public class RedisMessageContainer {
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, ChatMessageListener listener) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        Topic chatTopic = new ChannelTopic("test-channel");
        container.addMessageListener(listener, chatTopic);
        return container;
    }
}
