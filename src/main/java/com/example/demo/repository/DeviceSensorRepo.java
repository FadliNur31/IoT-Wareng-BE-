package com.example.demo.repository;

import com.example.demo.entity.Device;
import com.example.demo.entity.DeviceSensor;
import com.example.demo.enums.sensorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeviceSensorRepo extends JpaRepository<DeviceSensor, UUID> {

    List<DeviceSensor> findByDevice(Device dis);

    Optional<DeviceSensor> findByDeviceAndSensorType(Device device, sensorType sensorType);
}
