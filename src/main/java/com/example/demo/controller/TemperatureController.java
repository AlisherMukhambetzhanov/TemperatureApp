package com.example.demo.controller;

import com.example.demo.model.Temperature;
import com.example.demo.repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class TemperatureController {

    @Autowired
    private TemperatureRepository temperatureRepository;

    @MessageMapping("/temperature")
    @SendTo("/topic/temperature")
    public Temperature send(Temperature message) throws Exception {
        return temperatureRepository.save(message);
    }
}
