package com.example.demo.service;

import com.example.demo.entity.Device;
import com.example.demo.entity.DeviceSensor;
import com.example.demo.entity.SensorReading;
import com.example.demo.enums.sensorType;
import com.example.demo.exceptionhandler.ResourceNotFound;
import com.example.demo.repository.DeviceRepo;
import com.example.demo.repository.DeviceSensorRepo;
import com.example.demo.repository.SensorReadingRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SensorReadingService {
    private DeviceRepo deviceRepo;
    private DeviceSensorService deviceSensorService;
    private SensorReadingRepo sensorReadingRepo;
    private DeviceSensorRepo deviceSensorRepo;


    public List<SensorReading> findByDeviceSensor(UUID sensorId){
        DeviceSensor sensor = deviceSensorRepo.findById(sensorId)
                .orElseThrow(() -> new ResourceNotFound("Device not found"));

        return sensorReadingRepo.findByDeviceSensor(sensor);

    }

    public SensorReading createReading(UUID deviceId, sensorType sensorType, Double value) {

        Device device = deviceRepo.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFound("Device not found"));

        DeviceSensor deviceSensor =
                deviceSensorService.getOrCreateDeviceSensor(device, sensorType);

        SensorReading reading = new SensorReading();
        reading.setDeviceSensor(deviceSensor);
        reading.setValue(value);

        return sensorReadingRepo.save(reading);
    }

}
