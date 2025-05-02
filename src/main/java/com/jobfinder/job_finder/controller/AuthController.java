package com.jobfinder.job_finder.controller;

import com.jobfinder.job_finder.dto.LoginRequest;
import com.jobfinder.job_finder.dto.RegisterRequest;
import com.jobfinder.job_finder.entity.User;
import com.jobfinder.job_finder.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // Đăng ký
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid RegisterRequest registerRequest) {
        User registeredUser = userService.registerUser(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    // Đăng nhập
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody @Valid LoginRequest loginRequest) {
        User user = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(user);
    }
}