package com.example.demo.dto;

import com.example.demo.enums.sensorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class SensorReadingDTO {
    private UUID sensorReadingId;
    private Double value;
    private UUID deviceSensorId;
    private String location;
    private sensorType SensorType;
    private LocalDateTime time;
}
