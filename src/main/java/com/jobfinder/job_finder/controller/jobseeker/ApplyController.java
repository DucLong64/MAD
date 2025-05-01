package com.jobfinder.job_finder.controller.jobseeker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobfinder.job_finder.dto.jobposting.respond.RespondGetListJobForSeeker;
import com.jobfinder.job_finder.entity.job.Job;
import com.jobfinder.job_finder.service.job.JobService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/jobs")
public class ApplyController {

    @Autowired
    private JobService jobService;

    // danh sách công việc
    @GetMapping("/")
    public ResponseEntity<List<RespondGetListJobForSeeker>> getAllJobPostings() {
        List<RespondGetListJobForSeeker> jobs = jobService.getAllJobPostings();
        return ResponseEntity.ok(jobs);
    }

    // chi tiết công việc
    @GetMapping("/jobs/{jobID}")
    public ResponseEntity<Job> getJobDetails(@PathVariable Long jobId) {
        Job jobDetails = jobService.getJobPostings(jobId);
        return ResponseEntity.ok(jobDetails);
    }
    
    // Ứng tuyển
    @PostMapping("/jobs/{jobID}")
    public String postMethodName(@RequestBody String entity) {
        
        return entity;
    }
    
}
