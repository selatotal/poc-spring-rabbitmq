package br.com.selat.pocspringrabbitmqconsumer.contract.v1;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class Message {

    private UUID id;
    private UUID deviceId;
    private String deviceCode;
    private Instant dateTime;
    private String content;
}
