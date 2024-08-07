package com.project.workflow.controllers;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.SignalEventReceivedBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Timed;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/bpm")
public class BPMResource {

    private final RuntimeService runtimeService;

    public BPMResource(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @PostMapping("/signals")
    @Timed(millis = 0L)
    public ResponseEntity<Void> sendSignal(@RequestBody String signal) {
        SignalEventReceivedBuilder signalEvent = runtimeService.createSignalEvent(signal);
        signalEvent.setVariables(Map.of("date", LocalDateTime.now()));
        signalEvent.send();
        return ResponseEntity.noContent().build();
    }
}