package app.site.ws;

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
public class WSChatService {
    @OnOpen
    public void onOpen(@PathParam("groupId") String groupId, Session session) throws IOException {
        session.getBasicRemote().sendText(String.format("welcome to chat group: %s", groupId));
    }

    @OnClose
    public void onClose(@PathParam("groupId") String groupId, Session session) throws IOException {
        session.getBasicRemote().sendText(String.format("you are closing chat channel which groupId is :%s", groupId));
    }

    @OnError
    public void onError(@PathParam("groupId") String groupId, Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(@PathParam("groupId") String groupId, String message, Session session) throws IOException {
        System.out.println(String.format("get message: %s", message));
        session.getBasicRemote().sendText(String.format("group(%s): %s", groupId, "hello, world!"));
    }
}
