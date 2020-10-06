package app.site.ws;

import app.site.cache.ChatMessageCache;
import app.site.service.ChatService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.util.Map;

/**
 * @author steve
 */
@Component
public class WSContext implements ApplicationContextAware {
    protected static StringRedisTemplate REDIS_TEMPLATE;
    protected static Map<String, Session> WS_SESSION_MAP;
    protected static ChatService CHAT_SERVICE;
    protected static ChatMessageCache CHAT_MESSAGE_CACHE;


    @SuppressWarnings("unchecked")
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        WS_SESSION_MAP = (Map<String, Session>) applicationContext.getBean("wsSessionMap");
        REDIS_TEMPLATE = applicationContext.getBean(StringRedisTemplate.class);
        CHAT_SERVICE = applicationContext.getBean(ChatService.class);
        CHAT_MESSAGE_CACHE = applicationContext.getBean(ChatMessageCache.class);
    }
}
