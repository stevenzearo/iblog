package app.site.ws;

import app.site.service.ChatService;
import app.site.web.interceptor.LoginRequired;
import app.web.error.ConflictException;
import app.web.error.WebException;
import org.springframework.beans.factory.annotation.Autowired;
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
public class WSChatService {
    @Autowired
    ChatService chatService;

    @OnOpen
    public void onOpen(@PathParam("groupId") String groupId, Session session) throws IOException, WebException {
        chatService.onOpen(groupId, session);
    }

    @OnClose
    public void onClose(@PathParam("groupId") String groupId, Session session) throws IOException, WebException {
        chatService.onClose(groupId, session);
    }

    @OnError
    public void onError(@PathParam("groupId") String groupId, Session session, Throwable throwable) throws ConflictException, IOException {
        chatService.onError(groupId, session, throwable);
    }

    @OnMessage
    public void onMessage(@PathParam("groupId") String groupId, String message, Session session) throws WebException, IOException {
        chatService.onMessage(groupId, message, session);
    }
}
