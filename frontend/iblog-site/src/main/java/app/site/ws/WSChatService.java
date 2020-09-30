package app.site.ws;

import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.Topic;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @author steve
 */
/*
 * this is a test for redis pub/sub function
 *
 * */
@Component
@ServerEndpoint("/ws/chat/{groupId}")
public class WSChatService extends WSComponent implements ApplicationContextAware {

    @OnOpen
    public void onOpen(@PathParam("groupId") String groupId, Session session) throws IOException {
        // todo get userId;
        WS_SESSION_MAP.put(groupId, session);
        // todo update group info in redis
        session.getBasicRemote().sendText(String.format("welcome to chat group: %s", groupId));
    }

    @OnClose
    public void onClose(@PathParam("groupId") String groupId, Session session) throws IOException {
        // todo get userId;
        String userId = "user-0001";
        WS_SESSION_MAP.remove(userId);
        // todo update group info in redis
    }

    @OnError
    public void onError(@PathParam("groupId") String groupId, Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(@PathParam("groupId") String groupId, String message, Session session) {
        // todo publish message to redis
        Topic chatTopic = new ChannelTopic("ws-chat");
        REDIS_TEMPLATE.convertAndSend(chatTopic.getTopic(), message);
    }
}
