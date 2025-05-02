package com.jobfinder.job_finder.service;

import com.jobfinder.job_finder.dto.RegisterRequest;
import com.jobfinder.job_finder.entity.User;
import com.jobfinder.job_finder.repository.UserRepository;
import com.jobfinder.job_finder.util.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Đăng ký người dùng
    public User registerUser(RegisterRequest registerRequest) {
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new RuntimeException("Tên đăng nhập đa tồn tại");
        }
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(registerRequest.getPassword()));
        user.setRole(registerRequest.getRole());
        return userRepository.save(user);
    }

    // Đăng nhập người dùng
    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return user;
    }
    // // Cập nhật hồ sơ người dùng
    // public User updateProfile(Long userId, UserDTO userDTO) {
    //     User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

    //     if (user.getRole() == Role.JOB_SEEKER) {
    //         JobSeeker jobSeeker = (JobSeeker) user;
    //         jobSeeker.setProfilePicture(userDTO.getProfilePicture());
    //         jobSeeker.setPhoneNumber(userDTO.getPhoneNumber());
    //         jobSeeker.setBirthDate(userDTO.getBirthDate());
    //         jobSeeker.setWorkExperience(userDTO.getWorkExperience());
    //         jobSeeker.setEducation(userDTO.getEducation());
    //         jobSeeker.setSkills(userDTO.getSkills());
    //         jobSeeker.setLanguages(userDTO.getLanguages());
    //         jobSeeker.setCertifications(userDTO.getCertifications());
    //         jobSeeker.setCvFile(userDTO.getCvFile());
    //     } else if (user.getRole() == Role.RECRUITER) {
    //         Recruiter recruiter = (Recruiter) user;
    //         recruiter.setCompanyName(userDTO.getCompanyName());
    //         recruiter.setCompanyAddress(userDTO.getCompanyAddress());
    //         recruiter.setCompanyPhoneNumber(userDTO.getCompanyPhoneNumber());
    //         recruiter.setCompanyLogo(userDTO.getCompanyLogo());
    //     }
    //     return userRepository.save(user);
    // }

    // // Lấy thông tin hồ sơ người dùng
    // public User getUserProfile(Long userId) {
    //     User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    //     if(user instanceof JobSeeker) {
    //         JobSeeker jobSeeker = (JobSeeker) user;
    //         return jobSeeker;
    //     }
    //     else if(user instanceof Recruiter) {
    //         Recruiter recruiter = (Recruiter) user;
    //         return recruiter;
    //     }
    //     return user;
    // }
    // // Lấy tất cả hồ sơ người dùng
    // public List<User> getAllUserProfiles() {
    //     return userRepository.findAll();  // Lấy tất cả người dùng
    // }

    public Role getUserRole(Long userId) {
        return userRepository.findById(userId).get().getRole();  // Lấy vai trò của người dùng
    }

}