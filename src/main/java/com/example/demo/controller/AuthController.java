package com.example.demo.controller;

import com.example.demo.entity.LoginResponse;
import com.example.demo.entity.User;
import com.example.demo.entity.UserPrincipal;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@AllArgsConstructor
public class AuthController extends BaseController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;


    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody User user) {
        User registeredUser = authenticationService.signup(user);

        return success(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody User user) {

        authenticationService.authenticate(user);

        UserPrincipal principal = (UserPrincipal) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String jwtToken = jwtService.generateToken(principal);

        String role = principal.getAuthorities()
                .stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .map(r -> r.replace("ROLE_", ""))
                .orElse(null);

        LoginResponse loginResponse = new LoginResponse(
                jwtToken,
                role,
                jwtService.getExpirationTime()
        );

        return success(loginResponse);
    }


}