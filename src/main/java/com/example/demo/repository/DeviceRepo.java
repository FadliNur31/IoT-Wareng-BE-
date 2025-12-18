package com.example.demo.repository;

import com.example.demo.entity.Device;
import com.example.demo.entity.User;
import com.example.demo.enums.role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeviceRepo extends JpaRepository<Device, UUID> {

    List<Device> findByUser(User user);

    List<Device> findByUserRole(role userRole);

    List<Device> findByUserInAndUserRole(List<User> users, role role);
}
