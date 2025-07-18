package br.com.selat.pocspringrabbitmqproducer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "poc.producer")
@Data
public class PocProducerConfiguration {
    private String exchange;
    private String routingKeyPrefix;
    private String incomingQueuePrefix;
}
