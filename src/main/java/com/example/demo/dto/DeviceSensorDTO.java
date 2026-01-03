package com.example.demo.dto;

import com.example.demo.enums.sensorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class DeviceSensorDTO {
    private UUID deviceSensorId;
    private sensorType sensorType;
}
