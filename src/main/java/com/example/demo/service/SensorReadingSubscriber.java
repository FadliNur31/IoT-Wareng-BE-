package com.example.demo.service;

import com.example.demo.dto.SensorPayload;
import com.example.demo.enums.sensorType;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.UUID;

@Service
@AllArgsConstructor
public class SensorReadingSubscriber {

    private final IMqttClient mqttClient;
    private final SensorReadingService sensorReadingService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() throws Exception {
        subscribe();
    }

    private void subscribe() throws Exception {
        mqttClient.subscribe("IoT-Wareng/sensor", (topic, message) -> {

            String payload = new String(message.getPayload());

            try {
                SensorPayload data =
                        objectMapper.readValue(payload, SensorPayload.class);

                System.out.println("Received MQTT message:");

                sensorReadingService.createReading(
                        UUID.fromString(data.getDeviceId()),
                        sensorType.valueOf(data.getSensorType()),
                        data.getValue()
                );

            } catch (Exception e) {
                System.err.println("Invalid MQTT payload: " + payload);
                e.printStackTrace();
            }
        });
    }
}

