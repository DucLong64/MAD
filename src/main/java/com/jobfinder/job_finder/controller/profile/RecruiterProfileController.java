package com.jobfinder.job_finder.controller.profile;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobfinder.job_finder.dto.profile.request.CreateRecruiterProfileRequest;
import com.jobfinder.job_finder.dto.profile.request.UpdateRecruiterProfileRequest;
import com.jobfinder.job_finder.entity.profile.RecruiterProfile;
import com.jobfinder.job_finder.service.profile.RecruiterProfileService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/recruiter/{userId}/profile")
public class RecruiterProfileController {

    private final RecruiterProfileService recruiterProfileService;

    // Constructor
    public RecruiterProfileController(@Qualifier("recruiterProfileService") 
                            RecruiterProfileService recruiterProfileService) {
        this.recruiterProfileService = recruiterProfileService;
    }

    @PostMapping
    public RecruiterProfile CreateProfile(@PathVariable("userId") Long userId,@Valid @RequestBody CreateRecruiterProfileRequest request) {
        return recruiterProfileService.createProfile(userId, request);
    }
    
    @PutMapping
    public RecruiterProfile UpdateProfile(@PathVariable("userID") Long userid, @RequestBody UpdateRecruiterProfileRequest request) {
        return recruiterProfileService.updateProfile(userid, request);
    }

    @GetMapping
    public RecruiterProfile GetProfile(@PathVariable("userID") Long userId) {
        return recruiterProfileService.getProfile(userId);
    }
}
