package br.com.selat.pocspringrabbitmqproducer;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class PocSpringRabbitMQProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocSpringRabbitMQProducerApplication.class, args);
	}

}
