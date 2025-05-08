package com.jobfinder.job_finder.controller;

import com.jobfinder.job_finder.dto.*;
import com.jobfinder.job_finder.dto.request.DtoRegister;
import com.jobfinder.job_finder.dto.request.UserLogin;
import com.jobfinder.job_finder.dto.response.ApiResponseLogin;
import com.jobfinder.job_finder.dto.response.ApiResponseRegister;
import com.jobfinder.job_finder.entity.User;
import com.jobfinder.job_finder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public ResponseEntity<ApiResponseRegister> register(@RequestBody DtoRegister dtoRegister) {
        ApiResponseRegister response = userService.registerUser(dtoRegister);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Đăng nhập

    @PostMapping("/login")
    public ResponseEntity<ApiResponseLogin> login(@RequestBody UserLogin userLogin) {
        try {
            ApiResponseLogin response= userService.loginUser(userLogin);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        } catch (Exception e) {
            // Trả về lỗi nếu có sự cố
            ApiResponseLogin response = new ApiResponseLogin("error", e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
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