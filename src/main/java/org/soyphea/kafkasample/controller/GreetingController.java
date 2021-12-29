package org.soyphea.kafkasample.controller;

import lombok.AllArgsConstructor;
import org.soyphea.kafkasample.domain.Event;
import org.soyphea.kafkasample.producer.MyProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.soyphea.kafkasample.User;

import java.util.stream.IntStream;

@RestController
@AllArgsConstructor
public class GreetingController {

    private final MyProducer myProducer;

    @GetMapping("/greeting/{name}/topic/{topic}")
    ResponseEntity<?> sendTheMessage(@PathVariable("name") String name,
                                     @PathVariable("topic") String topic) {
        Event event =new Event(topic,
                new User(name));
        myProducer.sendMessage(topic, event);
        return ResponseEntity.ok("Done");
    }

}
