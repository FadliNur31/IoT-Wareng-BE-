package com.example.demo.dto;

import com.example.demo.enums.role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDTO {
    private String username;
    private role role;
    private String VillageName;
    private Long id;

    public UserDTO(String username, role role, Long userId) {
        this.username = username;
        this.role = role;
        this.id = userId;
    }
}
