package br.com.selat.pocspringrabbitmqconsumer;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class PocSpringRabbitMQConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocSpringRabbitMQConsumerApplication.class, args);
	}

}
