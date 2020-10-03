package app.site.ws;

import app.web.error.ConflictException;
import app.web.error.WebException;
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
@ServerEndpoint("/ws/chat/{groupId}/user-auth/{authId}")
public class WSChatService extends WSContext {
    @OnOpen
    public void onOpen(@PathParam("groupId") String groupId, @PathParam("authId") String authId, Session session) throws IOException, WebException {
        CHAT_SERVICE.onOpen(groupId, authId, session);
    }

    @OnClose
    public void onClose(@PathParam("groupId") String groupId, @PathParam("authId") String authId, Session session) throws IOException, WebException {
        CHAT_SERVICE.onClose(groupId, authId, session);
    }

    @OnError
    public void onError(@PathParam("groupId") String groupId, @PathParam("authId") String authId, Session session, Throwable throwable) throws ConflictException, IOException {
        CHAT_SERVICE.onError(groupId, authId, session, throwable);
    }

    @OnMessage
    public void onMessage(@PathParam("groupId") String groupId, @PathParam("authId") String authId, String message, Session session) throws WebException, IOException {
        CHAT_SERVICE.onMessage(groupId, authId, message, session);
    }
}
