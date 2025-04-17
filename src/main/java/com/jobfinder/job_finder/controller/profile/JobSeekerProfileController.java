package com.jobfinder.job_finder.controller.profile;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobfinder.job_finder.dto.profile.request.CreateJobSeekerProfileRequest;
import com.jobfinder.job_finder.dto.profile.request.UpdateJobSeekerProfileRequest;
import com.jobfinder.job_finder.entity.profile.JobSeekerProfile;
import com.jobfinder.job_finder.service.profile.JobSeekerProfileService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/jobseeker/{userID}/profile")
public class JobSeekerProfileController {
     private final JobSeekerProfileService jobSeekerProfileService;

    // Constructor
    public JobSeekerProfileController(@Qualifier("jobSeekerProfileService") 
                                        JobSeekerProfileService jobSeekerProfileService) {
        this.jobSeekerProfileService = jobSeekerProfileService;
    }

    @PostMapping
    public JobSeekerProfile CreateProfile(@PathVariable("userId") Long userId,@Valid @RequestBody CreateJobSeekerProfileRequest request) {
        return jobSeekerProfileService.createProfile(userId, request);
    }
    
    @PutMapping
    public JobSeekerProfile UpdateProfile(@PathVariable("userID") Long userid, @RequestBody UpdateJobSeekerProfileRequest request) {
        return jobSeekerProfileService.updateProfile(userid, request);
    }

    @GetMapping
    public JobSeekerProfile GetProfile(@PathVariable("userID") Long userId) {
        return jobSeekerProfileService.getProfile(userId);
    }
}
