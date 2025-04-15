package com.jobfinder.job_finder.controller;

import com.jobfinder.job_finder.dto.UserDTO;
import com.jobfinder.job_finder.entity.User;
import com.jobfinder.job_finder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // Đăng ký
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDTO userDTO) {
        User registeredUser = userService.registerUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    // Đăng nhập
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam String email, @RequestParam String password) {
        User user = userService.loginUser(email, password);
        return ResponseEntity.ok(user);
    }

    // Cập nhật hồ sơ
    @PutMapping("/profile/{userId}")
    public ResponseEntity<User> updateProfile(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        User updatedUser = userService.updateProfile(userId, userDTO);
        return ResponseEntity.ok(updatedUser);
    }
    // Xem hồ sơ người dùng
    @GetMapping("/profile/{userId}")
    public ResponseEntity<User> getUserProfile(@PathVariable Long userId) {
        User user = userService.getUserProfile(userId);
        return ResponseEntity.ok(user);
    }
    // Lấy tất cả hồ sơ người dùng
    @GetMapping("/profile")
    public ResponseEntity<List<User>> getAllUserProfiles() {
        List<User> users = userService.getAllUserProfiles();
        return ResponseEntity.ok(users);
    }

}