package org.soyphea.kafkasample.controller;

import lombok.AllArgsConstructor;
import org.soyphea.kafkasample.producer.MyProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class GreetingController {

    private final MyProducer myProducer;

    @PostMapping("/greeting/{name}")
    ResponseEntity<?> sendTheMessage(@PathVariable("name") String name){
        myProducer.sendMessage(String.format("Hello %s",name));
        return ResponseEntity.ok("Done");
    }

}
