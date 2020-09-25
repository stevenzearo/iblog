package app.site;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author steve
 */
@Component
public class ChatMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println(String.format("patern: %s received message: %s", new String(pattern), message.toString()));
    }
}
