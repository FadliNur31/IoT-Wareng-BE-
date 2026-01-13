package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.Device;
import com.example.demo.entity.SensorReading;
import com.example.demo.entity.User;
import com.example.demo.enums.role;
import com.example.demo.exceptionhandler.ResourceNotFound;
import com.example.demo.repository.DeviceRepo;
import com.example.demo.repository.SensorReadingRepo;
import com.example.demo.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class DeviceService {

    private final UserRepo userRepo;
    private final DeviceRepo deviceRepo;
    private final SensorReadingRepo sensorReadingRepo;

    public List<DeviceDTO> getAllDevice() {
        return deviceRepo.findByUserRole(role.USER)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<DeviceDTO> getDevicesByUserId(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User not found"));

        return deviceRepo.findByUser(user)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<DeviceDTO> getDevicesByVillageId(Long villageId) {
        List<User> users = userRepo.getAllByVillageVillageId(villageId);

        return deviceRepo.findByUserInAndUserRole(users, role.USER)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public void createDevice(Device device, Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User not found"));

        device.setUser(user);
        deviceRepo.save(device);
    }


    private DeviceDTO toDTO(Device device) {
        return new DeviceDTO(
                device.getDeviceId(),
                device.getDeviceName(),
                device.getUser().getUserId(),
                device.getUser().getUsername()
        );
    }

    public DashboardSensorReadingDTO getDeviceReadings(UUID deviceId) {

        Device device = deviceRepo.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Device not found"));

        List<SensorsDTO> sensors = device.getDeviceSensorList().stream()
                .map(sensor -> new SensorsDTO(
                        sensor.getSensorType(),
                        sensor.getSensorReadingList().stream()
                                .sorted(Comparator.comparing(SensorReading::getCreatedAt))
                                .map(r -> new SensorsDashboard(
                                        r.getCreatedAt(),
                                        r.getValue()
                                ))
                                .toList()
                ))
                .toList();

        return new DashboardSensorReadingDTO(
                device.getDeviceId(),
                device.getDeviceName(),
                sensors
        );
    }

    public List<DeviceStats> getDeviceStats() {

        return sensorReadingRepo.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        r -> r.getDeviceSensor().getDevice().getDeviceName(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .map(e -> new DeviceStats(
                        e.getKey(),
                        e.getValue()
                ))
                .toList();
    }

}

