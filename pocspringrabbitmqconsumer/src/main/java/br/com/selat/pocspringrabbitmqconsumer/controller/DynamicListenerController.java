package br.com.selat.pocspringrabbitmqconsumer.controller;

import br.com.selat.pocspringrabbitmqconsumer.dynamiclistener.DynamicListenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dynamic-listener")
@RequiredArgsConstructor
public class DynamicListenerController {

    private final DynamicListenerService dynamicListenerService;

    @PostMapping("/start/{identifier}")
    public ResponseEntity<Object> startListening(@PathVariable("identifier") String identifier) {
        dynamicListenerService.startListener(identifier);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/stop/{identifier}")
    public ResponseEntity<Object> stopListening(@PathVariable("identifier") String identifier) {
        dynamicListenerService.stopListener(identifier);
        return ResponseEntity.noContent().build();
    }
}
