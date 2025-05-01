package com.jobfinder.job_finder.controller.recruiter;


import com.jobfinder.job_finder.dto.jobposting.request.CreateJobPostingRequest;
import com.jobfinder.job_finder.dto.jobposting.request.UpdateJobPostingRequest;
import com.jobfinder.job_finder.entity.job.JobPosting;
import com.jobfinder.job_finder.service.Job.JobPostingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recruiter")
public class JobPostingController {
    @Autowired
    private JobPostingService jobPostingService;

    // Đăng tin tuyển dụng
    @PostMapping("/post-job")
    public ResponseEntity<JobPosting> postJob(@RequestBody CreateJobPostingRequest request, @RequestParam Long recruiterId) {
        JobPosting createdJob = jobPostingService.createJobPosting(request, recruiterId);
        return ResponseEntity.ok(createdJob);
    }
    
    // Lấy danh sách tin tuyển dụng của nhà tuyển dụng
    @GetMapping("/jobs/{id}")
    public ResponseEntity<List<JobPosting>> getAllJobPostings(@PathVariable Long id) {
        List<JobPosting> jobs = jobPostingService.getJobPostings(id);
        return ResponseEntity.ok(jobs);
    }

    // Cập nhật tin tuyển dụng
    @PutMapping("/update-job/{jobId}")
    public ResponseEntity<JobPosting> updateJob(@PathVariable Long jobId, @RequestParam Long recruiterId, @RequestBody  UpdateJobPostingRequest request) {
        JobPosting updatedJob = jobPostingService.updateJobPosting(jobId, recruiterId, request);
        return ResponseEntity.ok(updatedJob);
    }

    // Hủy tin tuyển dụng
    @DeleteMapping("/delete-job/{jobId}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long jobId) {
        jobPostingService.deleteJobPosting(jobId);
        return ResponseEntity.noContent().build();
    }
    // Lay tat ca cac tin
    @GetMapping("/jobs/all")
    public ResponseEntity<List<JobPosting>> getJobPostings() {
        List<JobPosting> jobPostings = jobPostingService.getAllJobPostings();
        return ResponseEntity.ok(jobPostings);
    }
    @GetMapping("/jobs")
    public ResponseEntity<List<JobPosting>> getActiveJobPostings() {
        List<JobPosting> jobs = jobPostingService.getAllJobPostingsAndActiveTure();
        return ResponseEntity.ok(jobs);
    }
}
