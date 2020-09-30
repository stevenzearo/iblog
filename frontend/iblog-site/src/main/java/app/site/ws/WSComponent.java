package app.site.ws;

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
public class WSComponent implements ApplicationContextAware {
    protected static StringRedisTemplate REDIS_TEMPLATE;
    protected static Map<String, Session> WS_SESSION_MAP;

    @SuppressWarnings("unchecked")
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        WS_SESSION_MAP = (Map<String, Session>) applicationContext.getBean("wsSessionMap");
        REDIS_TEMPLATE = applicationContext.getBean(StringRedisTemplate.class);
    }
}
