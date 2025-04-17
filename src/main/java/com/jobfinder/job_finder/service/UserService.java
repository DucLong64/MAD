package com.jobfinder.job_finder.service;

import com.jobfinder.job_finder.dto.UserDTO;
import com.jobfinder.job_finder.entity.JobSeeker;
import com.jobfinder.job_finder.entity.Recruiter;
import com.jobfinder.job_finder.entity.User;
import com.jobfinder.job_finder.repository.UserRepository;
import com.jobfinder.job_finder.util.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Đăng ký người dùng
    public User registerUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }
        User user = new User();
        // Đăng ký theo vai trò
        if (userDTO.getRole() == Role.JOB_SEEKER) {
            user = new JobSeeker();  // Tạo JobSeeker cho người tìm việc
        } else if (userDTO.getRole() == Role.RECRUITER) {
            user = new Recruiter();  // Tạo Recruiter cho nhà tuyển dụng
        }

        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));  // Mã hóa mật khẩu
        user.setRole(userDTO.getRole());
        return userRepository.save(user);
    }

    // Đăng nhập người dùng
    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return user;
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

    public Role getUserRole(Long userId) {
        return userRepository.findById(userId).get().getRole();  // Lấy vai trò của người dùng
    }

}