package app.site;

import app.site.ws.WSConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.Map;

/**
 * @author steve
 */
@Configuration
public class AdditionalBean {
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, ChatMessageListener listener) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        Topic chatTopic = new ChannelTopic(WSConfig.CHAT_CHANNEL);
        container.addMessageListener(listener, chatTopic);
        return container;
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean(name = "wsSessionMap")
    @Qualifier(value = "wsSessionMap")
    public Map<String, Session> wsSessionMap() {
        return new HashMap<>();
    }
}
