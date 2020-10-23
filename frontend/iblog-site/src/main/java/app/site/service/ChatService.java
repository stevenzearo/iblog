package app.site.service;

import app.site.cache.ChatGroupCache;
import app.site.cache.ChatMessageCache;
import app.site.cache.RedisTransaction;
import app.site.cache.User;
import app.site.cache.WSChatContentMessage;
import app.site.cache.WSChatMessage;
import app.site.cache.WSChatMessageType;
import app.site.cache.WSUserJoinMessage;
import app.site.web.Context;
import app.site.web.ErrorCodes;
import app.site.ws.WSConfig;
import app.site.ws.WSContext;
import app.web.error.ConflictException;
import app.web.error.NotFoundException;
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
    @Autowired
    ChatGroupCache chatGroupCache;

    public void onOpen(String groupId, String authId, Session session) throws IOException, WebException {
        User currentUser = userService.getCurrentUser(authId);
        if (currentUser == null) {
            session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "login required, please login first"));
            throw new ConflictException(ErrorCodes.LOGIN_REQUIRED, "login required, please login first");
        }

        chatGroupCache.findById(groupId).orElseThrow(() -> new NotFoundException(ErrorCodes.CHAT_GROUP_NOT_FOUND, String.format("chat group not found, groupId = %s", groupId)));

        // todo move to group ops
        SetOperations<String, String> opsForSet = REDIS_TEMPLATE.opsForSet();
        opsForSet.add(String.format(Context.CHAT_GROUP_SET + ":%s", groupId));
        opsForSet.add(String.format(Context.CHAT_GROUP_SET + ":%s", groupId), String.valueOf(currentUser.id));

        WS_SESSION_MAP.put(String.format("%s:%d", groupId, currentUser.id), session);
        WSChatMessage chatMessage = new WSChatMessage();
        chatMessage.id = UUID.randomUUID().toString();
        chatMessage.groupId = groupId;
        chatMessage.type = WSChatMessageType.USER_JOIN;
        chatMessage.userJoinMessage = buildUserJoinMessage(currentUser);
        Topic chatTopic = new ChannelTopic(WSConfig.CHAT_CHANNEL);

        chatMessageCache.save(chatMessage);
        REDIS_TEMPLATE.convertAndSend(chatTopic.getTopic(), chatMessage.id);
    }

    public void onClose(String groupId, String authId, Session session) throws WebException {
        User currentUser = userService.getCurrentUser(authId);
        if (currentUser == null)
            throw new ConflictException(ErrorCodes.LOGIN_REQUIRED, "login required, please login first");

        chatGroupCache.findById(groupId).orElseThrow(() -> new NotFoundException(ErrorCodes.CHAT_GROUP_NOT_FOUND, String.format("chat group not found, groupId = %s", groupId)));

        // todo move to group ops
        SetOperations<String, String> opsForSet = REDIS_TEMPLATE.opsForSet();
        Boolean containsGroup = opsForSet.isMember(Context.CHAT_GROUP_SET, groupId);
        if (containsGroup == null) throw new WebException("server error");
        opsForSet.remove(String.format(Context.CHAT_GROUP_SET + ":%s", groupId), String.valueOf(currentUser.id));
        Long size = opsForSet.size(String.format(Context.CHAT_GROUP_SET + ":%s", groupId));
        if (size == null) throw new WebException("server error.");
        if (size == 0) {
            REDIS_TEMPLATE.delete(String.format(Context.CHAT_GROUP_SET + ":%s", groupId));
        }

        WS_SESSION_MAP.remove(String.format("%s:%d", groupId, currentUser.id));
    }

    public void onError(String groupId, String authId, Session session, Throwable throwable) throws IOException {
        User currentUser = userService.getCurrentUser(authId);
        session.close(new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION, "server error."));
        WS_SESSION_MAP.remove(String.format("%s:%d", groupId, currentUser.id));
        throwable.printStackTrace();
    }

    public void onMessage(String groupId, String authId, String message, Session session) throws WebException, IOException {
        User currentUser = userService.getCurrentUser(authId);

        String[] strings = message.split("\000");
        validateMessage(session, strings);

        String toUserId = null;
        String content;
        if (strings.length == 2) {
            toUserId = strings[0];
            content = strings[1];
        } else {
            content = strings[0];
        }

        User toUser = null;
        if (toUserId != null) {
            toUser = userService.get(Long.valueOf(toUserId));
        }

        WSChatMessage WSChatMessage = buildChatMessageCache(groupId, currentUser, content, toUser);
        Topic chatTopic = new ChannelTopic(WSConfig.CHAT_CHANNEL);
        chatMessageCache.save(WSChatMessage);
        REDIS_TEMPLATE.convertAndSend(chatTopic.getTopic(), WSChatMessage.id);
    }

    private void validateMessage(Session session, String[] strings) throws IOException, ConflictException {
        if (strings.length != 1 && strings.length != 2) {
            session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "invalid chat message format"));
            throw new ConflictException(ErrorCodes.CHAT_MESSAGE_FORMAT_INVALID, "invalid chat message format");
        }
        if (strings.length == 2) {
            String toUserId = strings[0];
            if (toUserId.isBlank()) {
                session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "invalid chat message format"));
                throw new ConflictException(ErrorCodes.CHAT_MESSAGE_FORMAT_INVALID, "invalid chat message format");
            }
        }
    }

    private WSChatMessage buildChatMessageCache(String groupId, User currentUser, String content, User toUser) {
        WSChatMessage chatMessage = new WSChatMessage();
        chatMessage.id = UUID.randomUUID().toString();
        chatMessage.type = WSChatMessageType.CHAT;
        chatMessage.groupId = groupId;
        chatMessage.chatContentMessage = buildChatContentMessage(currentUser, toUser, content);
        chatMessage.createdTime = ZonedDateTime.now();
        return chatMessage;
    }

    private WSUserJoinMessage buildUserJoinMessage(User currentUser) {
        WSChatMessage.ChatMember chatMember = new WSChatMessage.ChatMember();
        chatMember.userId = currentUser.id;
        chatMember.name = currentUser.name;
        WSUserJoinMessage userJoinMessage = new WSUserJoinMessage();
        userJoinMessage.chatMember = chatMember;
        return userJoinMessage;
    }

    private WSChatContentMessage buildChatContentMessage(User currentUser, User toUser, String content) {
        WSChatMessage.ChatMember from = new WSChatMessage.ChatMember();
        from.userId = currentUser.id;
        from.name = currentUser.name;
        WSChatContentMessage chatContentMessage = new WSChatContentMessage();
        chatContentMessage.from = from;
        chatContentMessage.content = content;
        if (toUser != null) {
            WSChatMessage.ChatMember to = new WSChatMessage.ChatMember();
            to.userId = toUser.id;
            to.name = toUser.name;
            chatContentMessage.to = to;
        }
        return chatContentMessage;
    }
}
