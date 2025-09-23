package com.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.config.JwtUtil;
import com.hrms.dto.LoginRequest;
import com.hrms.dto.LoginResponse;
import com.hrms.dto.RegisterRequest;
import com.hrms.entity.User;
import com.hrms.enums.Role;
import com.hrms.repository.UserRepository;

@RestController
@RequestMapping("/hrms/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
    	
    	if (request.getUsername() == null || request.getUsername().trim().isEmpty() ||
    	        request.getPassword() == null || request.getPassword().trim().isEmpty() ||
    	        request.getRole() == null || request.getRole().trim().isEmpty()) {
    	        return "Username, Password, and Role are required!";
    	}
    	
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return "Username already exists!";
        }
        
        Role role;
        try {
            role = Role.valueOf(request.getRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            return "Invalid role! Allowed roles: SUPER_ADMIN, ADMIN, EMPLOYEE, MANAGER";
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);

        userRepository.save(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public Object login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Generate JWT token
            final String jwt = jwtUtil.generateToken(user.getUsername());

            // Return JWT and role
            return new LoginResponse(user.getRole().name(), jwt);

        } catch (Exception e) {
            return "Invalid username or password!";
        }
    }
}