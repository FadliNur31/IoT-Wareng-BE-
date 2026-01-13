package com.example.demo.controller;


import com.example.demo.dto.DeviceDTO;
import com.example.demo.dto.DeviceStats;
import com.example.demo.entity.Device;
import com.example.demo.entity.UserPrincipal;
import com.example.demo.service.DeviceService;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/device")
@AllArgsConstructor
public class DeviceController extends BaseController {

    DeviceService deviceService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<?> getDevice() {
        return success(deviceService.getAllDevice());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PostMapping("/create")
    public ResponseEntity<?> createDevice(@RequestParam Long userId, @RequestParam @NonNull String deviceName) {
        Device device = new Device();
        device.setDeviceName(deviceName);
        deviceService.createDevice(device, userId );
        return success("Create device success");
    }

    @GetMapping("/getAllforManager")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ResponseEntity<?> getDeviceforManager(Authentication authentication) {

        UserPrincipal principal =
                (UserPrincipal) authentication.getPrincipal();

        Long villageId = principal.getVillageId();

        return success(
                deviceService.getDevicesByVillageId(villageId)
        );
    }

    @GetMapping("/getAllforFarmer")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
    public ResponseEntity<?> getDeviceforFarmer(Authentication authentication) {

        UserPrincipal principal =
                (UserPrincipal) authentication.getPrincipal();

        Long userId = principal.getId();

        return success(
                deviceService.getDevicesByUserId(userId)
        );
    }

    @GetMapping("/getAllByVillage")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<?> getDeviceByVillage(@RequestParam @NonNull Long villageId) {

        return success(
                deviceService.getDevicesByVillageId(villageId)
        );
    }

    @GetMapping("/getAllByUser")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public ResponseEntity<?> getDeviceByUser(@RequestParam @NonNull Long userId) {

        return success(
                deviceService.getDevicesByUserId(userId)
        );
    }

    @GetMapping("/readings")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
    public ResponseEntity<?> getDeviceReadings(@RequestParam @NonNull UUID deviceId) {
       return success(deviceService.getDeviceReadings(deviceId));
    }

    @GetMapping("/stats")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
    public ResponseEntity<?> getStats() {
        return success(deviceService.getDeviceStats());
    }




}
