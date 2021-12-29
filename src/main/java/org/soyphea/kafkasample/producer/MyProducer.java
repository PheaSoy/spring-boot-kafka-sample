package org.soyphea.kafkasample.producer;

import lombok.extern.slf4j.Slf4j;
import org.soyphea.kafkasample.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@Slf4j
public class MyProducer {

    @Autowired
    private KafkaTemplate<String, Event> kafkaTemplate;

    public void sendMessage(String topicName, Event message) {

        ListenableFuture<SendResult<String, Event>> future =
                kafkaTemplate.send(topicName, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Event>>() {

            @Override
            public void onSuccess(SendResult<String, Event> result) {
                log.info("Sent message = {} with offset : {}",message,result.getRecordMetadata().offset());
            }
            @Override
            public void onFailure(Throwable ex) {
              log.error("Failed to send the message with error :{}",ex.getMessage());
            }
        });
    }
}
