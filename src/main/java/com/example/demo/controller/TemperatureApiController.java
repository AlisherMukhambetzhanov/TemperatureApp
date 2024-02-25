package com.example.demo.controller;

import com.example.demo.model.Temperature;
import com.example.demo.repository.TemperatureRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/temperature")
public class TemperatureApiController {

    private final TemperatureRepository temperatureRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public TemperatureApiController(TemperatureRepository temperatureRepository, SimpMessagingTemplate messagingTemplate) {
        this.temperatureRepository = temperatureRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Temperature> updateTemperature(@PathVariable Long id, @RequestBody Temperature temperature) {
        Temperature updatedTemperature = temperatureRepository.findById(id)
                .map(temp -> {
                    temp.setTemperatureValue(temperature.getTemperatureValue());
                    return temperatureRepository.save(temp);
                })
                .orElseGet(() -> {
                    temperature.setId(id);
                    return temperatureRepository.save(temperature);
                });

        // Отправка обновления через WebSocket
        messagingTemplate.convertAndSend("/topic/temperature", updatedTemperature);
        return ResponseEntity.ok(updatedTemperature);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Temperature> getTemperature(@PathVariable Long id) {
        return temperatureRepository.findById(id)
                .map(temperature -> ResponseEntity.ok(temperature))
                .orElse(ResponseEntity.notFound().build());
    }

}

