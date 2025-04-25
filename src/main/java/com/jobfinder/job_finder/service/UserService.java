package com.jobfinder.job_finder.service;

import com.jobfinder.job_finder.dto.*;
import com.jobfinder.job_finder.entity.JobSeeker;
import com.jobfinder.job_finder.entity.Recruiter;
import com.jobfinder.job_finder.util.JwtUtil;
import com.jobfinder.job_finder.util.Role;
import com.jobfinder.job_finder.entity.User;
import com.jobfinder.job_finder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;

    // Đăng ký người dùng
    public ApiResponseRegister registerUser(DtoRegister dtoRegister) {
        if (userRepository.findByEmail(dtoRegister.getEmail()).isPresent()) {
            return new ApiResponseRegister("Email already in use", false, HttpStatus.BAD_REQUEST.value());  // Trả về lỗi nếu email đã tồn tại
        }
        User user = new User();
        // Đăng ký theo vai trò
        if (dtoRegister.getRole() == Role.JOB_SEEKER) {
            user = new JobSeeker();  // Tạo JobSeeker cho người tìm việc
        } else if (dtoRegister.getRole() == Role.RECRUITER) {
            user = new Recruiter();  // Tạo Recruiter cho nhà tuyển dụng
        }

        user.setFullName(dtoRegister.getFullName());
        user.setEmail(dtoRegister.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(dtoRegister.getPassword()));  // Mã hóa mật khẩu
        user.setRole(dtoRegister.getRole());
        userRepository.save(user);
        return new ApiResponseRegister("User registered successfully", true, HttpStatus.CREATED.value());  // Thông báo thành công
    }

    // Đăng nhập người dùng
    public ApiResponseLogin loginUser(UserLogin userLogin) {
        User user = userRepository.findByEmail(userLogin.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!new BCryptPasswordEncoder().matches(userLogin.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token= jwtUtil.generateToken(user.getEmail());
        UserDTOResponse userDTOResponse= new UserDTOResponse(user.getId()
                                                    ,user.getFullName()
                                                    ,user.getEmail()
                                                    ,user.getRole().toString()
                                                    ,token);
        return new ApiResponseLogin("success", "Login successful", userDTOResponse);
    }
    // Cập nhật hồ sơ người dùng
    public User updateProfile(Long userId, UserDTO userDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() == Role.JOB_SEEKER) {
            JobSeeker jobSeeker = (JobSeeker) user;
            jobSeeker.setProfilePicture(userDTO.getProfilePicture());
            jobSeeker.setPhoneNumber(userDTO.getPhoneNumber());
            jobSeeker.setBirthDate(userDTO.getBirthDate());
            jobSeeker.setWorkExperience(userDTO.getWorkExperience());
            jobSeeker.setEducation(userDTO.getEducation());
            jobSeeker.setSkills(userDTO.getSkills());
            jobSeeker.setLanguages(userDTO.getLanguages());
            jobSeeker.setCertifications(userDTO.getCertifications());
            jobSeeker.setCvFile(userDTO.getCvFile());
        } else if (user.getRole() == Role.RECRUITER) {
            Recruiter recruiter = (Recruiter) user;
            recruiter.setCompanyName(userDTO.getCompanyName());
            recruiter.setCompanyAddress(userDTO.getCompanyAddress());
            recruiter.setCompanyPhoneNumber(userDTO.getCompanyPhoneNumber());
            recruiter.setCompanyLogo(userDTO.getCompanyLogo());
        }
        return userRepository.save(user);
    }

    // Lấy thông tin hồ sơ người dùng
    public User getUserProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        if(user instanceof JobSeeker) {
            JobSeeker jobSeeker = (JobSeeker) user;
            return jobSeeker;
        }
        else if(user instanceof Recruiter) {
            Recruiter recruiter = (Recruiter) user;
            return recruiter;
        }
        return user;
    }
    // Lấy tất cả hồ sơ người dùng
    public List<User> getAllUserProfiles() {
        return userRepository.findAll();  // Lấy tất cả người dùng
    }

}