package org.soyphea.kafkasample;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.soyphea.kafkasample.domain.Event;
import org.soyphea.kafkasample.producer.MyProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import java.util.stream.IntStream;

@SpringBootApplication
@Slf4j
public class KafkaSampleApplication {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    public static void main(String[] args) {
        SpringApplication.run(KafkaSampleApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(@DefaultValue("my_topic") @Value("${kafka.topic}") String topic,
                                        MyProducer myProducer) {
        return args -> {
            log.info("Sending the message.");
            IntStream.range(1, 20).forEach(i -> {
                Event event =new Event(topic,
                        new User("Chan Dara"));

                myProducer.sendMessage(topic, event);
            });
        };
    }
}

