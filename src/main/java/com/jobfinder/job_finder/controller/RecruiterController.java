package com.jobfinder.job_finder.controller;

import com.jobfinder.job_finder.converter.JobSeekerDTOConverter;
import com.jobfinder.job_finder.dto.JobSeekerDTO;
import com.jobfinder.job_finder.entity.JobSeeker;
import com.jobfinder.job_finder.entity.User;
import com.jobfinder.job_finder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recruiter")
public class RecruiterController {
    @Autowired
    private UserService userService;
    @Autowired
    private JobSeekerDTOConverter jobSeekerDTOConverter;

    @GetMapping("/seeker/{seeker_id}")
    public ResponseEntity<JobSeekerDTO> getSeeker(@PathVariable long seeker_id) {
        JobSeeker seeker =(JobSeeker) userService.getUserProfile(seeker_id);
        JobSeekerDTO jobSeekerDTO= jobSeekerDTOConverter.toDTO(seeker);
        return ResponseEntity.ok(jobSeekerDTO);
    }

}
