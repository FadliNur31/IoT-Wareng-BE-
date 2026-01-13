package com.example.demo.dto;


import com.example.demo.enums.sensorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SensorsDTO {
    private sensorType sensorType;
    private List<SensorsDashboard> readings;
}
