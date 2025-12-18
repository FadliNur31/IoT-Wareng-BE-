package com.example.demo.service;


import com.example.demo.entity.Device;
import com.example.demo.entity.DeviceSensor;
import com.example.demo.enums.sensorType;
import com.example.demo.exceptionhandler.ResourceNotFound;
import com.example.demo.repository.DeviceRepo;
import com.example.demo.repository.DeviceSensorRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DeviceSensorService {
    private DeviceRepo deviceRepo;
    private DeviceSensorRepo deviceSensorRepo;

    public List<DeviceSensor> getDeviceSensorByDeviceId(UUID deviceId) {
        Device dis = deviceRepo.findById(deviceId).orElseThrow(() -> new ResourceNotFound("Device not found"));

        return deviceSensorRepo.findByDevice(dis);
    }

    public DeviceSensor getOrCreateDeviceSensor(Device device, sensorType sensorType) {

        return deviceSensorRepo
                .findByDeviceAndSensorType(device, sensorType)
                .orElseGet(() -> {
                    DeviceSensor sensor = new DeviceSensor();
                    sensor.setDevice(device);
                    sensor.setSensorType(sensorType);
                    return deviceSensorRepo.save(sensor);
                });
    }


}
