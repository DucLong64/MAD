package com.jobfinder.job_finder.controller;

import com.jobfinder.job_finder.converter.JobSeekerDTOConverter;
import com.jobfinder.job_finder.dto.JobSeekerDTO;
import com.jobfinder.job_finder.entity.Application;
import com.jobfinder.job_finder.entity.JobSeeker;
import com.jobfinder.job_finder.entity.User;
import com.jobfinder.job_finder.service.ApplicationService;
import com.jobfinder.job_finder.service.UserService;
import com.jobfinder.job_finder.util.ApplicationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recruiter")
public class RecruiterController {
    @Autowired
    private UserService userService;
    @Autowired
    private JobSeekerDTOConverter jobSeekerDTOConverter;
    @Autowired
    private ApplicationService applicationService;
    @GetMapping("/seeker/{seeker_id}")
    public ResponseEntity<JobSeekerDTO> getSeeker(@PathVariable long seeker_id) {
        JobSeeker seeker =(JobSeeker) userService.getUserProfile(seeker_id);
        JobSeekerDTO jobSeekerDTO= jobSeekerDTOConverter.toDTO(seeker);
        return ResponseEntity.ok(jobSeekerDTO);
    }
    // Phê duyệt hoặc từ chối đơn ứng tuyển
    @PutMapping("/update-application-status/{applicationId}")
    public ResponseEntity<Map<String, Object>> updateApplicationStatus(
            @PathVariable Long applicationId,
            @RequestParam ApplicationStatus status) {

        Map<String, Object> response = new HashMap<>();
        try {
            Application updatedApplication = applicationService.updateApplicationStatus(applicationId, status);

            response.put("status", "success");
            response.put("message", "Trạng thái đơn ứng tuyển đã được cập nhật.");
            response.put("application", updatedApplication.getStatus());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Đã xảy ra lỗi khi cập nhật trạng thái đơn ứng tuyển: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    // Lấy tất cả các đơn ứng tuyển cho một tin tuyển dụng
    @GetMapping("/applications/{jobPostingId}")
    public ResponseEntity<List<Application>> getApplicationsByJobPosting(@PathVariable Long jobPostingId) {
        try {
            List<Application> applications = applicationService.getApplicationsByJobPosting(jobPostingId);
            if (applications.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);  // Trả về mã 204 nếu không có đơn ứng tuyển
            }
            return ResponseEntity.ok(applications);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);  // Trả về mã lỗi 500 nếu có ngoại lệ
        }
    }

}
