package com.jobfinder.job_finder.service.profile;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jobfinder.job_finder.repository.profile.JobSeekerProfileRepository;

@Service
@Qualifier("jobSeekerProfileService")
public class JobSeekerProfileService implements ProfileService {

    private final JobSeekerProfileRepository jobSeekerProfileRepository;

    public JobSeekerProfileService(JobSeekerProfileRepository jobSeekerProfileRepository) {
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
    }

    @Override
    public void createProfile() {
        // Logic to create a job seeker profile
    }

    @Override
    public void updateProfile() {
        // Logic to update a job seeker profile
    }

    @Override
    public void getProfile() {
        // Logic to get a job seeker profile
    }

}
