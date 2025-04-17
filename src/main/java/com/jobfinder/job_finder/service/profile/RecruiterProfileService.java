package com.jobfinder.job_finder.service.profile;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jobfinder.job_finder.dto.profile.request.CreateRecruiterProfileRequest;
import com.jobfinder.job_finder.dto.profile.request.UpdateRecruiterProfileRequest;
import com.jobfinder.job_finder.entity.User;
import com.jobfinder.job_finder.entity.profile.RecruiterProfile;
import com.jobfinder.job_finder.repository.UserRepository;
import com.jobfinder.job_finder.repository.profile.RecruiterProfileRepository;

@Service
@Qualifier("recruiterProfileService")
public class RecruiterProfileService {

    private final RecruiterProfileRepository recruiterProfileRepository;
    private final UserRepository userRepository;

    public RecruiterProfileService(RecruiterProfileRepository recruiterProfileRepository, UserRepository userRepository) {
        this.recruiterProfileRepository = recruiterProfileRepository;
        this.userRepository = userRepository;
    }

    // tạo mới hồ sơ nhà tuyển dụng
    public RecruiterProfile createProfile(Long recruiterID, CreateRecruiterProfileRequest request) {
        RecruiterProfile recruiterProfile = new RecruiterProfile();
        recruiterProfile.setName(request.getCompanyName());
        recruiterProfile.setSdt(request.getContactPhoneNumber());
        recruiterProfile.setEmail(request.getContactEmail());
        recruiterProfile.setImage(request.getCompanyLogoUrl());
        recruiterProfile.setAddress(request.getCompanyAddress());
        recruiterProfile.setDescription(request.getCompanyDescription());
        recruiterProfile.setOperationField(request.getCompanyOperationField());
        recruiterProfile.setLink(request.getCompanyWebsite());

        // Lấy thông tin người dùng từ cơ sở dữ liệu
        User user = userRepository.findById(recruiterID)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        // Gán người dùng cho hồ sơ nhà tuyển dụng
        recruiterProfile.setUser(user);

        return recruiterProfileRepository.save(recruiterProfile);
    }

    // cập nhật hồ sơ nhà tuyển dụng
    public RecruiterProfile updateProfile(Long recruiterID, UpdateRecruiterProfileRequest request) {
        RecruiterProfile recruiterProfile = recruiterProfileRepository.findByRecruiterId(recruiterID)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hồ sơ"));
        recruiterProfile.setName(request.getCompanyName());
        recruiterProfile.setSdt(request.getContactPhoneNumber());
        recruiterProfile.setEmail(request.getContactEmail());
        recruiterProfile.setImage(request.getCompanyLogoUrl());
        recruiterProfile.setAddress(request.getCompanyAddress());
        recruiterProfile.setDescription(request.getCompanyDescription());
        recruiterProfile.setOperationField(request.getCompanyOperationField());
        recruiterProfile.setLink(request.getCompanyWebsite());

        return recruiterProfileRepository.save(recruiterProfile);
    }

    public RecruiterProfile getProfile(Long recruiterID) {
        return recruiterProfileRepository.findByRecruiterId(recruiterID)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hồ sơ"));
    }

}
