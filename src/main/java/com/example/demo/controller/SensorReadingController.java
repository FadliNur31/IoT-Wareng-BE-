package com.example.demo.controller;


import com.example.demo.dto.SensorPayload;
import com.example.demo.enums.sensorType;
import com.example.demo.service.SensorReadingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @PostMapping("/manual")
    public ResponseEntity<?> createManualReading(
            @RequestBody SensorPayload request
    ) {
        sensorReadingService.createReading(
                UUID.fromString(request.getDeviceId()),
                sensorType.valueOf(request.getSensorType()),
                request.getValue()
        );

        return success("Sensor reading created manually");
    }

}
