package app.site;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author steve
 */
@Configuration
public class AdditionalBean {
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, ChatMessageListener listener) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        Topic chatTopic = new ChannelTopic("test-channel");
        container.addMessageListener(listener, chatTopic);
        return container;
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
