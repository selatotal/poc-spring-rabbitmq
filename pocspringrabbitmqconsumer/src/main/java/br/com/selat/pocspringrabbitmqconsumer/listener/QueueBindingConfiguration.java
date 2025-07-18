package br.com.selat.pocspringrabbitmqconsumer.listener;

import br.com.selat.pocspringrabbitmqconsumer.config.PocConsumerConfiguration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueBindingConfiguration {

    @Bean
    public TopicExchange centralExchange(PocConsumerConfiguration configuration) {
        return new TopicExchange(configuration.getExchange());
    }

    @Bean
    public Queue listenerQueue(PocConsumerConfiguration configuration) {
        return new Queue(configuration.getQueue(), true);
    }

    @Bean
    public Binding binding(PocConsumerConfiguration configuration) {
        return BindingBuilder.bind(listenerQueue(configuration))
                .to(centralExchange(configuration))
                .with(configuration.getRoutingKey());
    }
}
