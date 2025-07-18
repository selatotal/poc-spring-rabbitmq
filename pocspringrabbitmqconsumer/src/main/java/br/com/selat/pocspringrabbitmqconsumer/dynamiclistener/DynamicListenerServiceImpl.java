package br.com.selat.pocspringrabbitmqconsumer.dynamiclistener;

import br.com.selat.pocspringrabbitmqconsumer.config.PocConsumerConfiguration;
import br.com.selat.pocspringrabbitmqconsumer.contract.exceptions.InternalErrorException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class DynamicListenerServiceImpl implements DynamicListenerService {

    private final ConnectionFactory connectionFactory;
    private final AmqpTemplate amqpTemplate;
    private final AmqpAdmin amqpAdmin;
    private final Map<String, SimpleMessageListenerContainer> activeListeners = new ConcurrentHashMap<>();
    private final PocConsumerConfiguration configuration;

    @Override
    public void startListener(String identifier) {
        var destination = configuration.getDynamicListenerIncomingKeyPrefix() + identifier;

        if (isListening(destination)) {
            throw new InternalErrorException("Listener already active for identifier: " + identifier);
        }

        amqpAdmin.declareQueue(new Queue(destination, true));

        var listener = new SimpleMessageListenerContainer();
        listener.setConnectionFactory(connectionFactory);
        listener.setQueueNames(destination);
        listener.setMessageListener(message -> {
            String body = new String(message.getBody(), StandardCharsets.UTF_8);
            log.info("Message received at {} - {}", destination, body);
        });
        listener.start();

        activeListeners.put(destination, listener);
        log.info("Listener started for identifier: {}", identifier);
    }

    @Override
    public void stopListener(String identifier) {
        var destination = configuration.getDynamicListenerIncomingKeyPrefix() + identifier;

        var listener = activeListeners.remove(destination);
        if (listener != null) {
            listener.stop();
        }
        log.info("Listener stopped for identifier: {}", identifier);
    }

    private boolean isListening(String identifier) {
        return activeListeners.containsKey(identifier);
    }
}
