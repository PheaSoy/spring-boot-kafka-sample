package org.soyphea.kafkasample.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyConsumer {

    @Value(value = "${kafka.topic}")
    private String topic;

    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.groupId}",
    containerFactory = "filterKafkaListenerContainerFactory")
    public void listenGroupFoo(String message) {
        log.info("Received Message:{} from the topic:{}", message,topic);
    }
}
