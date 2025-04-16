package com.jobfinder.job_finder.service.profile;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jobfinder.job_finder.dto.profile.request.CreateRecruiterProfileRequest;
import com.jobfinder.job_finder.dto.profile.request.ProfileRequest;
import com.jobfinder.job_finder.entity.profile.Profile;
import com.jobfinder.job_finder.entity.profile.RecruiterProfile;
import com.jobfinder.job_finder.repository.profile.RecruiterProfileRepository;

@Service
@Qualifier("recruiterProfileService")
public class RecruiterProfileService implements ProfileService {

    private final RecruiterProfileRepository recruiterProfileRepository;

    public RecruiterProfileService(RecruiterProfileRepository recruiterProfileRepository) {
        this.recruiterProfileRepository = recruiterProfileRepository;
    }
    
    @Override
    public RecruiterProfile createProfile(CreateRecruiterProfileRequest request) {
        // Logic to create a recruiter profile
        RecruiterProfile recruiterProfile = new RecruiterProfile();
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

    @Override
    public RecruiterProfile updateProfile() {
        // Logic to update a recruiter profile
    }

    @Override
    public RecruiterProfile getProfile() {
        // Logic to get a recruiter profile
    }

    @Override
    public Profile createProfile(ProfileRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createProfile'");
    }

}
