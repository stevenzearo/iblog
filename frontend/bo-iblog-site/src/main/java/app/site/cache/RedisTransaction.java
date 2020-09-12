package app.site.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author steve
 */
@Component
public class RedisTransaction {
    @Autowired
    StringRedisTemplate redisTemplate;

    public void transaction(TransactionSupplier transactionSupplier) {
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.multi();
        transactionSupplier.call();
        redisTemplate.exec();
        redisTemplate.setEnableTransactionSupport(false);
    }
}
