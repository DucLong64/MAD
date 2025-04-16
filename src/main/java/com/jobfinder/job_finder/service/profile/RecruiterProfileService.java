package com.jobfinder.job_finder.service.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jobfinder.job_finder.repository.profile.RecruiterProfileRepository;

@Service
@Qualifier("recruiterProfileService")
public class RecruiterProfileService implements ProfileService {

    private final RecruiterProfileRepository recruiterProfileRepository;

    @Autowired
    public RecruiterProfileService(RecruiterProfileRepository recruiterProfileRepository) {
        this.recruiterProfileRepository = recruiterProfileRepository;
    }
    
    @Override
    public void createProfile() {
        // Logic to create a recruiter profile
    }

    @Override
    public void updateProfile() {
        // Logic to update a recruiter profile
    }

    @Override
    public void getProfile() {
        // Logic to get a recruiter profile
    }

}
