package com.jobfinder.job_finder.service.profile;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jobfinder.job_finder.dto.profile.request.CreateJobSeekerProfileRequest;
import com.jobfinder.job_finder.dto.profile.request.UpdateJobSeekerProfileRequest;
import com.jobfinder.job_finder.entity.profile.JobSeekerProfile;
import com.jobfinder.job_finder.repository.UserRepository;
import com.jobfinder.job_finder.repository.profile.JobSeekerProfileRepository;
import com.jobfinder.job_finder.entity.User;

@Service
@Qualifier("jobSeekerProfileService")
public class JobSeekerProfileService {

    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final UserRepository userRepository;

    public JobSeekerProfileService(JobSeekerProfileRepository jobSeekerProfileRepository, UserRepository userRepository) {
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.userRepository = userRepository;
    }

    //tạo hồ sơ người tìm việc
    public JobSeekerProfile createProfile(Long jobSeekerID, CreateJobSeekerProfileRequest request) {
        JobSeekerProfile jobSeekerProfile = new JobSeekerProfile();
        jobSeekerProfile.setName(request.getFullName());
        jobSeekerProfile.setSdt(request.getPhoneNumber());
        jobSeekerProfile.setEmail(request.getEmail());
        jobSeekerProfile.setImage(request.getImgUrl());
        jobSeekerProfile.setDob(request.getDateOfBirth());
        jobSeekerProfile.setGender(request.getGender());
        jobSeekerProfile.setEducation(request.getEducation());
        jobSeekerProfile.setPathCV(request.getCvUrl());
        jobSeekerProfile.setLanguage(request.getLanguage());

        // Lấy thông tin người dùng từ cơ sở dữ liệu
        User user = userRepository.findById(jobSeekerID)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        // Gán người dùng cho hồ sơ nhà tuyển dụng
        jobSeekerProfile.setUser(user);

        return jobSeekerProfile;
    }

    //cập nhật hồ sơ
    public JobSeekerProfile updateProfile(Long jobSeekerID, UpdateJobSeekerProfileRequest request) {
        JobSeekerProfile jobSeekerProfile = jobSeekerProfileRepository.findByJobSeekerId(jobSeekerID)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hồ sơ"));
        jobSeekerProfile.setName(request.getFullName());
        jobSeekerProfile.setSdt(request.getPhoneNumber());
        jobSeekerProfile.setEmail(request.getEmail());
        jobSeekerProfile.setImage(request.getImgUrl());
        jobSeekerProfile.setDob(request.getDateOfBirth());
        jobSeekerProfile.setGender(request.getGender());
        jobSeekerProfile.setEducation(request.getEducation());
        jobSeekerProfile.setPathCV(request.getCvUrl());
        jobSeekerProfile.setLanguage(request.getLanguage());

        return jobSeekerProfileRepository.save(jobSeekerProfile);
        
    }

    // lấy hồ sơ
    public JobSeekerProfile getProfile(Long jobseekerId) {
        return jobSeekerProfileRepository.findByJobSeekerId(jobseekerId)
        .orElseThrow(() -> new RuntimeException("Không tìm thấy hồ sơ"));
    }

}
