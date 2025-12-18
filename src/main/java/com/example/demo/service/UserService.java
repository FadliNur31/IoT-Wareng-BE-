package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.entity.Village;
import com.example.demo.enums.role;
import com.example.demo.exceptionhandler.ResourceNotFound;
import com.example.demo.repository.UserRepo;
import com.example.demo.repository.VillageRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final VillageRepo villageRepo;

    public List<UserDTO> getUserByVillage(Long villageId) {

        Village village = villageRepo.findByVillageId(villageId);

        if (village == null) {
            throw new ResourceNotFound("Village not found");
        }

        return userRepo.getAllByVillage(village)
                .stream()
                .filter(user -> user.getRole() == role.USER)
                .map(this::toDTO)
                .toList();
    }

    public List<UserDTO> getAllUser() {
        return userRepo.findAllByRoleIn(List.of(role.USER, role.MANAGER))
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // ===== Mapper =====
    private UserDTO toDTO(User user) {
        return new UserDTO(
                user.getUsername(),
                user.getRole(),
                user.getVillage().getVillageName(),
                user.getUserId()
        );
    }
}

