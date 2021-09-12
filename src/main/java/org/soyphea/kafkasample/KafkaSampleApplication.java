package org.soyphea.kafkasample;

import org.soyphea.kafkasample.producer.MyProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KafkaSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaSampleApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(MyProducer myProducer){
		return args -> {
			myProducer.sendMessage("Hello World from Kafka Producer");
		};
	}
}
