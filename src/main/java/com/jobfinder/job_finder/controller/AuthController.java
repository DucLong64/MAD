package com.jobfinder.job_finder.controller;

import com.jobfinder.job_finder.dto.ApiResponseLogin;
import com.jobfinder.job_finder.dto.ApiResponseRegister;
import com.jobfinder.job_finder.dto.UserDTO;
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
    public ResponseEntity<ApiResponseRegister> register(@RequestBody UserDTO userDTO) {
        ApiResponseRegister response = userService.registerUser(userDTO);

        // Nếu đăng ký thành công, trả về HTTP status 201 (Created) và thông báo
        if (response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponseRegister(response.getMessage(), response.isSuccess(), response.getStatusCode()));
        } else {
            // Nếu có lỗi, trả về HTTP status 400 (Bad Request) và thông báo lỗi
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponseRegister(response.getMessage(), response.isSuccess(), response.getStatusCode()));
        }
    }

    // Đăng nhập
    @PostMapping("/login")
    public ResponseEntity<ApiResponseLogin> login(@RequestParam String email, @RequestParam String password) {
        try {
            ApiResponseLogin response= userService.loginUser(email, password);
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