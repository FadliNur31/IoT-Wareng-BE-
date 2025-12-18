package com.example.demo.controller;


import com.example.demo.service.DeviceSensorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/deviceSensor")
@AllArgsConstructor
public class DeviceSensorController extends BaseController {
    DeviceSensorService deviceSensorService;

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN', 'USER')")
    @GetMapping("/getAllByDeviceId")
    public ResponseEntity<?> getAllDeviceSensorByDeviceId(@RequestParam UUID deviceId) {
        return success(deviceSensorService.getDeviceSensorByDeviceId(deviceId));
    }

}
