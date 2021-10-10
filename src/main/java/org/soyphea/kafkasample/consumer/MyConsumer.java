package org.soyphea.kafkasample.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyConsumer {

    @Value(value = "${kafka.topic}")
    private String topic;

    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.groupId}")
    @RetryableTopic(attempts = "5", backoff = @Backoff(delay = 2_000, maxDelay = 10_000, multiplier = 2))
    public void listenGroupFoo(String message) {

        log.info("Received Message:{} from the topic:{} and partition: {}", message,topic);
        if(message.contains("boom")){
            log.error("boom!");
            throw new RuntimeException("boom!");
        }
    }

    @DltHandler
    public void listenDlt(String in, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                          @Header(KafkaHeaders.OFFSET) long offset) {
        log.info("DLT Received: {} from {} @ {}", in, topic, offset);
    }
}
