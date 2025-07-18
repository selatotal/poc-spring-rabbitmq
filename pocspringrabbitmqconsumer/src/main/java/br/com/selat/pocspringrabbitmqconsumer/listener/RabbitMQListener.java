package br.com.selat.pocspringrabbitmqconsumer.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class RabbitMQListener {

    @RabbitListener(queues = "${poc.consumer.queue}", autoStartup = "true")
    public void process(String message) {
        log.info("Received: {}", message);
    }
}
