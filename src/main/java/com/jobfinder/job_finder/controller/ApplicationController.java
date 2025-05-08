package com.jobfinder.job_finder.controller;

import com.jobfinder.job_finder.dto.response.ApiResponse;
import com.jobfinder.job_finder.entity.JobPosting;
import com.jobfinder.job_finder.entity.JobSeeker;
import com.jobfinder.job_finder.service.ApplicationService;
import com.jobfinder.job_finder.service.JobPostingService;
import com.jobfinder.job_finder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/applications")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private UserService userService;
    @Autowired
    private JobPostingService jobPostingService;

    @PostMapping("/apply")
    public ApiResponse applyForJob(@RequestParam Long jobSeekerId,
                                   @RequestParam Long jobPostingId) {
        JobSeeker jobSeeker = (JobSeeker) userService.getUserProfile(jobSeekerId);
        JobPosting jobPosting = jobPostingService.getJobPostingById(jobPostingId);
        if (jobSeeker == null || jobPosting == null) {
            return new ApiResponse("Fail", "Candidate or job not exist!", 400);
        }

        try {
            applicationService.applyForJob(jobSeeker, jobPosting);
            return new ApiResponse("Success","Application submitted successfully!", 200);

        } catch (IllegalStateException e) {
            return new ApiResponse("Fail", "You have submitted yet!", 400);
        }
    }
}