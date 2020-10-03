package app.site;

import app.site.cache.ChatMessage;
import app.site.web.Context;
import app.site.ws.WSContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

/**
 * @author steve
 */
@Component
public class ChatMessageListener extends WSContext implements MessageListener {
    private final Logger logger = LoggerFactory.getLogger(ChatMessageListener.class);

    @Override
    public void onMessage(Message message, byte[] pattern) {
        logger.debug(String.format("channel: %s received message: %s", new String(message.getChannel()), message.toString()));
        String messageId = new String(message.getBody());
        Optional<ChatMessage> chatMessageOptional = CHAT_MESSAGE_CACHE.findById(messageId);
        if (chatMessageOptional.isEmpty()) return;

        ChatMessage chatMessage = chatMessageOptional.get();
        SetOperations<String, String> opsForSet = REDIS_TEMPLATE.opsForSet();
        Set<String> groupUserIds = opsForSet.members(String.format(Context.CHAT_GROUP_SET + ":%s", chatMessage.groupId));
        if (groupUserIds == null) return;

        ObjectMapper objectMapper = new ObjectMapper();
        for (String id : groupUserIds) {
            if (id.equals(String.valueOf(chatMessage.from.userId))) {
                continue;
            }
            Session session = WS_SESSION_MAP.get(String.format("%s:%s", chatMessage.groupId, id));
            if (session == null) continue;
            if (!session.isOpen()) continue;
            try {
                session.getBasicRemote().sendText(objectMapper.writeValueAsString(chatMessage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
