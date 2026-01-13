package com.example.demo.service;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserRepo userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public User signup(User input) {
        input.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(input);
    }

    public Authentication authenticate(User input) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                input.getUsername(),
                                input.getPassword()
                        )
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authentication;
    }

}