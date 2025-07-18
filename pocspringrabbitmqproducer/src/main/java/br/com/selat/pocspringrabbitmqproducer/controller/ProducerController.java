package br.com.selat.pocspringrabbitmqproducer.controller;

import br.com.selat.pocspringrabbitmqproducer.contract.v1.Message;
import br.com.selat.pocspringrabbitmqproducer.contract.v1.ProducerMessageInput;
import br.com.selat.pocspringrabbitmqproducer.service.ProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/producer")
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @PostMapping("/outgoing")
    public ResponseEntity<Message> sendOutgoing(@RequestBody ProducerMessageInput input) throws JsonProcessingException {
        return ResponseEntity.ok(producerService.sendOutgoing(input));
    }

    @PostMapping("/incoming")
    public ResponseEntity<Message> sendIncoming(@RequestBody ProducerMessageInput input) throws JsonProcessingException {
        return ResponseEntity.ok(producerService.sendIncoming(input));
    }
}
