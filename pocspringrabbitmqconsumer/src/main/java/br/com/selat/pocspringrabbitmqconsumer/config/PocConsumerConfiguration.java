package br.com.selat.pocspringrabbitmqconsumer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "poc.consumer")
@Data
public class PocConsumerConfiguration {
    private String exchange;
    private String queue;
    private String routingKey;
    private String dynamicListenerIncomingKeyPrefix;
}
