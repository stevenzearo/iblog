package app.site;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.util.HashMap;

/**
 * @author steve
 */
@Component
public class ChatMessageListener implements MessageListener {
    @Qualifier(value = "wsSessionMap")
    HashMap<String, Session> wsSessionMap;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        // todo subscribe message and send by ws
        wsSessionMap.forEach((k, v) -> System.out.println(k + " -> " + v));
        System.out.println(String.format("patern: %s received message: %s", new String(pattern), message.toString()));
    }
}
