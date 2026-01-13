package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DashboardSensorReadingDTO {
    private UUID deviceId;
    private String deviceName;
    private List<SensorsDTO> sensors;
}
