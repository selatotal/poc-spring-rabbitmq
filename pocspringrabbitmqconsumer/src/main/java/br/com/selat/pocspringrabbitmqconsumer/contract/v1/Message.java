package br.com.selat.pocspringrabbitmqconsumer.contract.v1;

import java.time.Instant;
import java.util.UUID;
import lombok.Data;

@Data
public class Message {

    private UUID id;
    private UUID deviceId;
    private String deviceCode;
    private Instant dateTime;
    private String content;
}
