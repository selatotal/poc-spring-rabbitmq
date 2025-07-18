package br.com.selat.pocspringrabbitmqproducer.service;

import br.com.selat.pocspringrabbitmqproducer.config.PocProducerConfiguration;
import br.com.selat.pocspringrabbitmqproducer.contract.v1.Message;
import br.com.selat.pocspringrabbitmqproducer.contract.v1.ProducerMessageInput;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProducerServiceImpl implements ProducerService {

    private static final String DEFAULT_RABBITMQ_EXCHANGE = "";
    private final PocProducerConfiguration configuration;
    private final AmqpTemplate amqpTemplate;
    private final AmqpAdmin amqpAdmin;
    private final ObjectMapper objectMapper;

    @Override
    public Message sendOutgoing(ProducerMessageInput input) throws JsonProcessingException {
        var message = toMessage(input);

        var destination = configuration.getRoutingKeyPrefix() + message.getDeviceCode();
        var messageJson = objectMapper.writeValueAsString(message);
        amqpTemplate.convertAndSend(configuration.getExchange(), destination, messageJson);
        log.info("Message sent to destination: {} - {} - {}", configuration.getExchange(), destination, messageJson);
        return message;
    }

    @Override
    public Message sendIncoming(ProducerMessageInput input) throws JsonProcessingException {
        var message = toMessage(input);
        var destination = configuration.getIncomingQueuePrefix() + message.getDeviceCode();
        amqpAdmin.declareQueue(new Queue(destination, true));

        var messageJson = objectMapper.writeValueAsString(message);
        amqpTemplate.convertAndSend(DEFAULT_RABBITMQ_EXCHANGE, destination, messageJson);
        log.info("Message sent to destination: {} - {}", destination, messageJson);
        return message;
    }

    private Message toMessage(ProducerMessageInput input) {
        var message = new Message();
        message.setId(UUID.randomUUID());
        message.setDeviceId(input.getDeviceId());
        message.setDeviceCode(input.getDeviceCode());
        message.setDateTime(input.getDateTime());
        message.setContent(input.getContent());
        return message;
    }
}
