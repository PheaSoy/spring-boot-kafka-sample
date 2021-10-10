package org.soyphea.kafkasample;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.soyphea.kafkasample.producer.MyProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import java.util.stream.IntStream;

@SpringBootApplication
@Slf4j
public class KafkaSampleApplication {

	private final TaskExecutor exec = new SimpleAsyncTaskExecutor();

	public static void main(String[] args) {
		SpringApplication.run(KafkaSampleApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(MyProducer myProducer){
		return args -> {
				myProducer.sendMessage("Hello World from Kafka Producer with i");
		};
	}

}

@Getter
@ToString
class User {
	String  name;
}
