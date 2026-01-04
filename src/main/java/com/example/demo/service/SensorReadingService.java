package com.example.demo.service;

import com.example.demo.dto.SensorReadingDTO;
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


    public List<SensorReadingDTO> findByDeviceSensor(UUID sensorId){
        DeviceSensor sensor = deviceSensorRepo.findById(sensorId)
                .orElseThrow(() -> new ResourceNotFound("Device not found"));

        String location = sensor.getDevice().getUser().getVillage().getVillageName() + ", " +
                sensor.getDevice().getUser().getVillage().getDistrict().getDistrictName() + ", " +
                sensor.getDevice().getUser().getVillage().getDistrict().getCity().getCityName() + ", " +
                sensor.getDevice().getUser().getVillage().getDistrict().getCity().getProvince().getProvinceName();

        return sensorReadingRepo.findByDeviceSensor(sensor)
                .stream()
                .map(v -> new SensorReadingDTO(
                        v.getSensorReadingId(),
                        v.getValue(),
                        v.getDeviceSensor().getDeviceSensorId(),
                        location,
                        v.getDeviceSensor().getSensorType(),
                        v.getCreatedAt()
                )).toList();


    }

    public void createReading(UUID deviceId, sensorType sensorType, Double value) {

        Device device = deviceRepo.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFound("Device not found"));

        DeviceSensor deviceSensor =
                deviceSensorService.getOrCreateDeviceSensor(device, sensorType);

        SensorReading reading = new SensorReading();
        reading.setDeviceSensor(deviceSensor);
        reading.setValue(value);
        sensorReadingRepo.save(reading);
    }

}
