package com.example.demo.service;

import com.example.demo.dto.DeviceDTO;
import com.example.demo.entity.Device;
import com.example.demo.entity.User;
import com.example.demo.enums.role;
import com.example.demo.exceptionhandler.ResourceNotFound;
import com.example.demo.repository.DeviceRepo;
import com.example.demo.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DeviceService {

    private final UserRepo userRepo;
    private final DeviceRepo deviceRepo;

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
}

