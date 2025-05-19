package com.jobfinder.job_finder.controller;

import com.jobfinder.job_finder.dto.JobPostingDTO;
import com.jobfinder.job_finder.dto.response.ApiResponse;
import com.jobfinder.job_finder.dto.response.ShiftDTO;
import com.jobfinder.job_finder.service.JobPostingService;
import com.jobfinder.job_finder.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seeker")
public class SeekerController {
    @Autowired
    private ShiftService shiftService;
    @Autowired
    private JobPostingService jobPostingService;
    @GetMapping("/schedule/{seekerId}")
    public ResponseEntity<ApiResponse<?>> getSchedule(@PathVariable Long seekerId) {
        List<ShiftDTO> shitfs = shiftService.getScheduleForSeeker(seekerId);
        return ResponseEntity.ok(new ApiResponse<>(200,"success",shitfs));
    }
    @GetMapping("/jobs/all")
    public ResponseEntity<ApiResponse<List<JobPostingDTO>>> getJobPostings() {
        List<JobPostingDTO> jobPostings = jobPostingService.getAllOpenJobPostings();
        ApiResponse<List<JobPostingDTO>> apiResponse = new ApiResponse<>(200,"Success", jobPostings);
        return ResponseEntity.ok(apiResponse);
    }

}
