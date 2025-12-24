package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SensorPayload {
    @JsonProperty("device_id")
    private String deviceId;

    @JsonProperty("sensor_type")
    private String sensorType;

    private Double value;
}