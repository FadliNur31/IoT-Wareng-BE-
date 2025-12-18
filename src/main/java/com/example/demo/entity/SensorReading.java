package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sensor_reading")
public class SensorReading extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID sensorReadingId;

    @Column(nullable = false)
    private Double value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_sensor_id", nullable = false)
    private DeviceSensor deviceSensor;

}
