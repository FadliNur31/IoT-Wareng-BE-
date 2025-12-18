package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class DeviceDTO {
    private UUID deviceId;
    private String deviceName;
    private Long userId;
    private String userName;
}
