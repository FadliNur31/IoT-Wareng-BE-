package com.example.demo.controller;


import com.example.demo.service.SensorReadingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/sensorReading")
@AllArgsConstructor
public class SensorReadingController extends BaseController {
    final SensorReadingService sensorReadingService;

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN', 'USER')")
    @GetMapping("/getByDeviceSensorId")
    public ResponseEntity<?> getSensorReadingsByDeviceSensorId(@RequestParam UUID deviceSensorId) {
        return success(sensorReadingService.findByDeviceSensor(deviceSensorId));
    }

}
