package com.jobfinder.job_finder.controller.recruiter;


import com.jobfinder.job_finder.dto.jobposting.request.CreateJobPostingRequest;
import com.jobfinder.job_finder.dto.jobposting.request.UpdateJobPostingRequest;
import com.jobfinder.job_finder.dto.jobposting.respond.RespondGetListJobForRecruiter;
import com.jobfinder.job_finder.entity.job.Job;
import com.jobfinder.job_finder.service.job.JobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recruiter/jobs")
public class JobPostingController {
    @Autowired
    private JobService jobService;

    // Lấy danh sách tin tuyển dụng của nhà tuyển dụng
    @GetMapping("/{recruiterID}")
    public ResponseEntity<List<RespondGetListJobForRecruiter>> getAllJobPostings(@PathVariable Long id) {
        List<RespondGetListJobForRecruiter> jobs = jobService.getJobForRecruiter(id);
        return ResponseEntity.ok(jobs);
    }

    // Đăng tin tuyển dụng
    @PostMapping("/{recruiterID}")
    public ResponseEntity<Job> postJob(@RequestBody CreateJobPostingRequest request, @RequestParam Long recruiterId) {
        Job createdJob = jobService.createJobPosting(request, recruiterId);
        return ResponseEntity.ok(createdJob);
    }

    // chi tiết công việc
    @GetMapping("/{jobID}")
    public ResponseEntity<Job> getJobDetails(@PathVariable Long jobId) {
        Job jobDetails = jobService.getJobPostings(jobId);
        return ResponseEntity.ok(jobDetails);
    }
    // Cập nhật tin tuyển dụng
    @PutMapping("/{recruiterID}/{jobID}")
    public ResponseEntity<Job> updateJob(@PathVariable Long jobId, @RequestParam Long recruiterId, @RequestBody  UpdateJobPostingRequest request) {
        Job updatedJob = jobService.updateJobPosting(jobId, recruiterId, request);
        return ResponseEntity.ok(updatedJob);
    }

    // Hủy tin tuyển dụng
    @DeleteMapping("/{recruiterID}/{jobID}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long jobId, @RequestParam Long recruiterId) {
        jobService.deleteJobPosting(jobId, recruiterId);
        return ResponseEntity.noContent().build();
    }
    // // Lay tat ca cac tin
    // @GetMapping("/jobs/all")
    // public ResponseEntity<List<JobPosting>> getJobPostings() {
    //     List<JobPosting> jobPostings = jobPostingService.getAllJobPostings();
    //     return ResponseEntity.ok(jobPostings);
    // }
    // @GetMapping("/jobs")
    // public ResponseEntity<List<JobPosting>> getActiveJobPostings() {
    //     List<JobPosting> jobs = jobPostingService.getAllJobPostingsAndActiveTure();
    //     return ResponseEntity.ok(jobs);
    // }
}
