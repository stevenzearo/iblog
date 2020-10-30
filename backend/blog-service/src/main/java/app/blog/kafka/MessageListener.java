package app.blog.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author steve
 */
@Component
public class MessageListener {
    @KafkaListener(groupId = "test-group-id", topics = {"test-topic"})
    public void listen(ConsumerRecord<String, String> record) {
        Optional<String> valueOptional = Optional.ofNullable(record.value());
        if (valueOptional.isEmpty()) return;

        String s = valueOptional.get();
        System.out.println(String.format("get message: %s", s));
    }
}
