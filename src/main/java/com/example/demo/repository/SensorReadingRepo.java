package com.example.demo.repository;

import com.example.demo.entity.DeviceSensor;
import com.example.demo.entity.SensorReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SensorReadingRepo extends JpaRepository<SensorReading, Long> {
    List<SensorReading> findByDeviceSensor(DeviceSensor sensor);
}
