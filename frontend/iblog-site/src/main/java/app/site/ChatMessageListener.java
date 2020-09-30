package app.site;

import app.site.ws.WSComponent;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author steve
 */
@Component
public class ChatMessageListener extends WSComponent implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        // todo subscribe message and send by ws
        WS_SESSION_MAP.forEach((k, v) -> {
            try {
                v.getBasicRemote().sendText(String.format("(%s): %s", k, "hello, world!"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(k + " -> " + v);
        });
        System.out.println(String.format("channel: %s received message: %s", new String(message.getChannel()), message.toString()));
    }
}
