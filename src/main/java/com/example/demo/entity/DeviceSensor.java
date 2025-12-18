package com.example.demo.entity;

import com.example.demo.enums.sensorType;
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
@Table(name = "device_sensor")
public class DeviceSensor extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID deviceSensorId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private sensorType sensorType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deviceId", nullable = false)
    private Device device;

    @OneToMany(mappedBy = "deviceSensor", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<SensorReading> sensorReadingList;

}
