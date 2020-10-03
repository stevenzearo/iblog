package app.site.service;

import app.site.cache.ChatMessage;
import app.site.cache.ChatMessageCache;
import app.site.cache.RedisTransaction;
import app.site.cache.User;
import app.site.web.Context;
import app.site.web.ErrorCodes;
import app.site.ws.WSConfig;
import app.site.ws.WSContext;
import app.web.error.ConflictException;
import app.web.error.WebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.Topic;
import org.springframework.stereotype.Service;

import javax.websocket.CloseReason;
import javax.websocket.Session;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * @author steve
 */

/*
 * WS open -> 1. add group to redis; 2. add session to bean container
 * on message -> 1. save message to redis, 2. publish message to certain group; 3. message listener subscribe message send ws message to certain group
 * on close -> remove group from redis; 2. remove session from bean container
 * on error -> remove group from redis; 2. remove session from bean container
 * */
@Service
public class ChatService extends WSContext {
    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;
    @Autowired
    RedisTransaction redisTransaction;
    @Autowired
    ChatMessageCache chatMessageCache;

    public void onOpen(String groupId, Session session) throws IOException, WebException {
        User currentUser = getCurrentUser(session);

        redisTransaction.transaction(() -> {
            SetOperations<String, String> opsForSet = REDIS_TEMPLATE.opsForSet();
            opsForSet.add(String.format(Context.CHAT_GROUP_SET + ":%s", groupId), String.valueOf(currentUser.id));
        });

        WS_SESSION_MAP.put(String.format("%s:%d", groupId, currentUser.id), session);
        session.getBasicRemote().sendText(String.format("welcome to chat group: %s", groupId));
    }

    public void onClose(String groupId, Session session) throws IOException, WebException {
        User currentUser = getCurrentUser(session);
        redisTransaction.transaction(() -> {
            SetOperations<String, String> opsForSet = REDIS_TEMPLATE.opsForSet();
            Boolean containsGroup = opsForSet.isMember(Context.CHAT_GROUP_SET, groupId);
            if (containsGroup == null) throw new WebException("server error");
            opsForSet.remove(String.format(Context.CHAT_GROUP_SET + ":%s", groupId), String.valueOf(currentUser.id));
            Long size = opsForSet.size(String.format(Context.CHAT_GROUP_SET + ":%s", groupId));
            if (size == null) throw new WebException("server error.");
            if (size == 0) {
                REDIS_TEMPLATE.delete(String.format(Context.CHAT_GROUP_SET + ":%s", groupId));
                chatMessageCache.deleteAllByGroupId(groupId);
            }
        });

        WS_SESSION_MAP.remove(String.format("%s:%d", groupId, currentUser.id));
    }

    public void onError(String groupId, Session session, Throwable throwable) throws ConflictException, IOException {
        User currentUser = getCurrentUser(session);
        WS_SESSION_MAP.remove(String.format("%s:%d", groupId, currentUser.id));
        throwable.printStackTrace();
    }

    public void onMessage(String groupId, String message, Session session) throws WebException, IOException {
        User currentUser = getCurrentUser(session);

        String[] strings = message.split("\0000");
        if (strings.length != 1 && strings.length != 2) {
            session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "invalid chat message format"));
            throw new ConflictException(ErrorCodes.CHAT_MESSAGE_FORMAT_INVALID, "invalid chat message format");
        }
        String toId = null;
        String content;
        if (strings.length == 2) {
            toId = strings[0];
            if (toId.isBlank()) {
                session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "invalid chat message format"));
                throw new ConflictException(ErrorCodes.CHAT_MESSAGE_FORMAT_INVALID, "invalid chat message format");
            }
            content = strings[1];
        } else {
            content = strings[0];
        }

        User toUser = null;
        if (toId != null) {
            toUser = userService.get(Long.valueOf(toId));
        }

        ChatMessage chatMessage = buildChatMessageCache(groupId, currentUser, content, toUser);

        Topic chatTopic = new ChannelTopic(WSConfig.CHAT_CHANNEL);
        redisTransaction.transaction(() -> {
            chatMessageCache.save(chatMessage);
            REDIS_TEMPLATE.convertAndSend(chatTopic.getTopic(), chatMessage);
        });
    }

    private ChatMessage buildChatMessageCache(String groupId, User currentUser, String content, User toUser) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.id = UUID.randomUUID().toString();
        chatMessage.groupId = groupId;
        ChatMessage.ChatMember from = new ChatMessage.ChatMember();
        from.userId = currentUser.id;
        from.name = currentUser.name;
        chatMessage.from = from;
        if (toUser != null) {
            ChatMessage.ChatMember to = new ChatMessage.ChatMember();
            to.userId = toUser.id;
            to.name = toUser.name;
            chatMessage.to = to;
        }
        chatMessage.content = content.getBytes();
        chatMessage.createdTime = ZonedDateTime.now();
        return chatMessage;
    }

    private User getCurrentUser(Session session) throws ConflictException, IOException {
        String authId = authService.getAuth(session);
        return userService.getCurrent(authId);
    }
}
