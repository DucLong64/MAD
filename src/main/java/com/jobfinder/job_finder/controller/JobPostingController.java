package com.jobfinder.job_finder.controller;


import com.jobfinder.job_finder.converter.JobPostingDTOConverter;
import com.jobfinder.job_finder.dto.JobPostingDTO;
import com.jobfinder.job_finder.entity.JobPosting;
import com.jobfinder.job_finder.entity.Recruiter;
import com.jobfinder.job_finder.entity.User;
import com.jobfinder.job_finder.service.JobPostingService;
import com.jobfinder.job_finder.service.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recruiter")
public class JobPostingController {
    @Autowired
    private JobPostingService jobPostingService;
    @Autowired
    private RecruiterService recruiterService;
    @Autowired
    private JobPostingDTOConverter jobPostingDTOConverter;
    // Đăng tin tuyển dụng
    @PostMapping("/post-job")
    public ResponseEntity<Map<String,Object>> postJob(@RequestBody JobPosting jobPosting, @RequestParam Long recruiterId) {
        Map<String,Object> response = new HashMap<>();
        try {
            // Tìm kiếm nhà tuyển dụng
            Recruiter recruiter = recruiterService.getRecruiterById(recruiterId);
            if (recruiter == null) {
                response.put("status", "error");
                response.put("message", "Recruiter not found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            jobPosting.setRecruiter(recruiter);
            // Tạo job tuyển dụng mới
            JobPostingDTO createdJob = jobPostingService.createJobPosting(jobPosting);
            // Tạo phản hồi thành công
            response.put("status", "success");
            response.put("message", "Job posted successfully.");
            response.put("job", createdJob);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có lỗi xảy ra
            response.put("status", "error");
            response.put("message", "An error occurred while posting the job: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    // Lấy danh sách tin tuyển dụng của nhà tuyển dụng
    @GetMapping("/jobs/{id}")
    public ResponseEntity<List<JobPostingDTO>> getAllJobPostings(@PathVariable Long id) {
        List<JobPostingDTO> jobs = jobPostingService.getJobPostings(id);
        return ResponseEntity.ok(jobs);
    }

    // Cập nhật tin tuyển dụng
    @PutMapping("/update-job/{jobId}")
    public ResponseEntity<Map<String, Object>> updateJob(@PathVariable Long jobId, @RequestBody JobPosting jobPosting) {

        Map<String, Object> response = new HashMap<>();
        try {
            // Tìm kiếm tin tuyển dụng cần cập nhật
            JobPostingDTO existingJob = jobPostingService.updateJobPosting(jobId, jobPosting);
            if (existingJob == null) {
                response.put("status", "error");
                response.put("message", "Job posting not found or does not belong to this recruiter.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            // Tạo phản hồi thành công
            response.put("status", "success");
            response.put("message", "Job updated successfully.");
            response.put("job", existingJob);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có lỗi xảy ra
            response.put("status", "error");
            response.put("message", "An error occurred while updating the job: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Hủy tin tuyển dụng
    @DeleteMapping("/delete-job/{jobId}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long jobId) {
        jobPostingService.deleteJobPosting(jobId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/job/{jobId}")
    public ResponseEntity<JobPostingDTO> getJobPosting(@PathVariable Long jobId) {
        return ResponseEntity.ok(jobPostingDTOConverter.toJobPostingDTO(jobPostingService.getJobPostingById(jobId)));
    }
    // Lay tat ca cac tin
    @GetMapping("/jobs/all")
    public ResponseEntity<List<JobPostingDTO>> getJobPostings() {
        List<JobPostingDTO> jobPostings = jobPostingService.getAllJobPostings();
        return ResponseEntity.ok(jobPostings);
    }
    // Lay tat ca cac tin con han
    @GetMapping("/jobs")
    public ResponseEntity<List<JobPostingDTO>> getActiveJobPostings() {
        List<JobPostingDTO> jobs = jobPostingService.getAllJobPostingsAndActiveTure();
        return ResponseEntity.ok(jobs);
    }
}
