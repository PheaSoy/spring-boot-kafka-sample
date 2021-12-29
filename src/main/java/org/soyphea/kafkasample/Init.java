package org.soyphea.kafkasample;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class Init implements ApplicationRunner {

    private final KafkaAdmin kafkaAdmin;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Bean initialize.");
        AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());
        List<NewTopic> topics = new ArrayList<>();
        topics.add(new NewTopic("topic1", 1, (short) 1));
        topics.add(new NewTopic("topic2", 1, (short) 1));
        topics.add(new NewTopic("sample_topic", 1, (short) 1));
        topics.add(new NewTopic("topic3", 1, (short) 1));
        adminClient.createTopics(topics);
    }
}
