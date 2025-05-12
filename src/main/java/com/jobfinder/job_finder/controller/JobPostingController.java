package com.jobfinder.job_finder.controller;


import com.jobfinder.job_finder.converter.JobPostingDTOConverter;
import com.jobfinder.job_finder.dto.JobPostingDTO;
import com.jobfinder.job_finder.dto.response.ApiResponse;
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
    public ResponseEntity<ApiResponse<?>> postJob(@RequestBody JobPosting jobPosting, @RequestParam Long recruiterId) {
        try {
            // Tìm kiếm nhà tuyển dụng
            Recruiter recruiter = recruiterService.getRecruiterById(recruiterId);
            if (recruiter == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(404, "Recruiter not found", null));
            }
            jobPosting.setRecruiter(recruiter);
            // Tạo job tuyển dụng mới
            JobPostingDTO createdJob = jobPostingService.createJobPosting(jobPosting);

            // Trả về phản hồi thành công
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(201, "Job posted successfully", createdJob));
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có lỗi xảy ra
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(500, "An error occurred while posting the job: " + e.getMessage(), null));
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
    public ResponseEntity<ApiResponse<?>> updateJob(@PathVariable Long jobId, @RequestBody JobPosting jobPosting) {
        try {
            // Tìm kiếm tin tuyển dụng cần cập nhật
            JobPostingDTO existingJob = jobPostingService.updateJobPosting(jobId, jobPosting);
            if (existingJob == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(404, "Job not found", null));
            }

            // Tạo phản hồi thành công
            return ResponseEntity.ok(new ApiResponse<>(200, "Job updated successfully", existingJob));
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có lỗi xảy ra
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(500, "An error occurred while updating the job: " + e.getMessage(), null));
        }
    }


    @DeleteMapping("/delete-job/{jobId}")
    public ResponseEntity<ApiResponse<?>> deleteJob(@PathVariable Long jobId) {
        try {
            jobPostingService.deleteJobPosting(jobId);
            // Trả về phản hồi thành công
            return ResponseEntity.ok(new ApiResponse<>(200, "Job posting deleted successfully", null));
        } catch (Exception e) {
            // Trả về lỗi nếu không tìm thấy tin tuyển dụng hoặc có lỗi khác
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Job posting not found: " + e.getMessage(), null));
        }
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
}
