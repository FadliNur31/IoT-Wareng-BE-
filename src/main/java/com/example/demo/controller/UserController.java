package com.example.demo.controller;


import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.entity.UserPrincipal;
import com.example.demo.enums.role;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController extends BaseController {
    private final AuthenticationService authenticationService;
    private UserService userService;
    private UserRepo userRepo;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/getAllforAdmin")
    public ResponseEntity<?> getAllUserforAdmin() {
        return success(userService.getAllUser());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/getAllByVillage")
    public ResponseEntity<?> getAllUserByVillage(@RequestParam Long villageId) {
        return success(userService.getUserByVillage(villageId));
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @GetMapping("/getAllforManager")
    public ResponseEntity<?> getAllUserforManager(Authentication authentication) {
        UserPrincipal principal =
                (UserPrincipal) authentication.getPrincipal();

        Long villageId = principal.getVillageId();
        return success(userService.getUserByVillage(villageId));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> createUser( @Valid @RequestBody User user) {
        User registeredUser = authenticationService.signup(user);
        UserDTO userDTO = new UserDTO(registeredUser.getUsername(), registeredUser.getRole(), registeredUser.getVillage().getVillageName(), registeredUser.getUserId());
        return success(userDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER', 'USER')")
    @GetMapping("/getMe")
    public ResponseEntity<?> getMe(Authentication authentication) {
        UserDTO userDTO;
        UserPrincipal principal =
                (UserPrincipal) authentication.getPrincipal();
        User user = userRepo.findUserByUserId(principal.getId());
        if(user.getRole() != role.ADMIN){
            userDTO = new UserDTO(user.getUsername(), user.getRole(), user.getVillage().getVillageName(), user.getUserId());
        }else{
            userDTO = new UserDTO(user.getUsername(), user.getRole(), user.getUserId());
        }

        return success(userDTO);
    }



}
