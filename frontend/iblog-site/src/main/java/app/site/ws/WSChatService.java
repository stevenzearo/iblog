package app.site.ws;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author steve
 */
/*
 * this is a test for redis pub/sub function
 *
 * */
@Component
@ServerEndpoint("/ws/chat/{groupId}")
public class WSChatService {
    @Resource(name = "wsSessionMap")
    HashMap<String, Session> wsSessionMap;

    @OnOpen
    public void onOpen(@PathParam("groupId") String groupId, Session session) throws IOException {
        // todo get userId;
        String userId = "user-0001";
        wsSessionMap.put(userId, session);
        // todo update group info in redis
        session.getBasicRemote().sendText(String.format("welcome to chat group: %s", groupId));
    }

    @OnClose
    public void onClose(@PathParam("groupId") String groupId, Session session) throws IOException {
        // todo get userId;
        String userId = "user-0001";
        wsSessionMap.remove(userId);
        // todo update group info in redis
        session.getBasicRemote().sendText(String.format("you are closing chat channel which groupId is :%s", groupId));
    }

    @OnError
    public void onError(@PathParam("groupId") String groupId, Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(@PathParam("groupId") String groupId, String message, Session session) throws IOException {
        // todo publish message to redis
        System.out.println(String.format("get message: %s", message));
        session.getBasicRemote().sendText(String.format("group(%s): %s", groupId, "hello, world!"));
    }
}
