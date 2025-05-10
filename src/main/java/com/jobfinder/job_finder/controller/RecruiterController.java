package com.jobfinder.job_finder.controller;

import com.jobfinder.job_finder.converter.ApplicationDTOConverter;
import com.jobfinder.job_finder.converter.JobSeekerDTOConverter;
import com.jobfinder.job_finder.dto.ApplicationDTO;
import com.jobfinder.job_finder.dto.JobSeekerDTO;
import com.jobfinder.job_finder.dto.response.ApiResponse;
import com.jobfinder.job_finder.entity.Application;
import com.jobfinder.job_finder.entity.JobPosting;
import com.jobfinder.job_finder.entity.JobSeeker;
import com.jobfinder.job_finder.entity.User;
import com.jobfinder.job_finder.service.ApplicationService;
import com.jobfinder.job_finder.service.JobPostingService;
import com.jobfinder.job_finder.service.ShiftService;
import com.jobfinder.job_finder.service.UserService;
import com.jobfinder.job_finder.util.ApplicationStatus;
import com.jobfinder.job_finder.util.JobStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recruiter")
public class RecruiterController {
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationDTOConverter applicationDTOConverter;
    @Autowired
    private JobSeekerDTOConverter jobSeekerDTOConverter;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private ShiftService shiftService;
    @Autowired
    private JobPostingService jobPostingService;

    @GetMapping("/seeker/{seeker_id}")
    public ResponseEntity<JobSeekerDTO> getSeeker(@PathVariable long seeker_id) {
        JobSeeker seeker =(JobSeeker) userService.getUserProfile(seeker_id);
        JobSeekerDTO jobSeekerDTO= jobSeekerDTOConverter.toDTO(seeker);
        return ResponseEntity.ok(jobSeekerDTO);
    }
    // Phê duyệt hoặc từ chối đơn ứng tuyển
    @PutMapping("/update-application-status/{applicationId}")
    public ResponseEntity<ApiResponse<?>> updateApplicationStatus(
            @PathVariable Long applicationId,
            @RequestParam ApplicationStatus status) {
        try {
            Application updatedApplication = applicationService.updateApplicationStatus(applicationId, status);

            if (status == ApplicationStatus.ACCEPTED) {
                JobSeeker jobSeeker = updatedApplication.getJobSeeker();  // Lấy ứng viên
                JobPosting jobPosting = updatedApplication.getJobPosting();  // Lấy công việc

                // Tạo hoặc cập nhật Shift cho ứng viên
                shiftService.createOrUpdateShiftForJobSeeker(jobSeeker, jobPosting);
            }
            // Trả về phản hồi thành công
            return ResponseEntity.ok(new ApiResponse<>(200, "Trạng thái đơn ứng tuyển đã được cập nhật.", updatedApplication.getStatus()));
        } catch (Exception e) {
            // Trả về lỗi nếu có sự cố
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(500, "Đã xảy ra lỗi khi cập nhật trạng thái đơn ứng tuyển: " + e.getMessage(), null));
        }

    }
    // Lấy tất cả các đơn ứng tuyển cho một tin tuyển dụng
    @GetMapping("/applications/{jobPostingId}")
    public ResponseEntity<List<ApplicationDTO>> getApplicationsByJobPosting(@PathVariable Long jobPostingId) {
        try {
            List<Application> applications = applicationService.getApplicationsByJobPosting(jobPostingId);
            List<ApplicationDTO> applicationDTOS = new ArrayList<>();
            for (Application application : applications) {
                ApplicationDTO tmp = applicationDTOConverter.convert(application);
                applicationDTOS.add(tmp);
            }
            if (applications.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);  // Trả về mã 204 nếu không có đơn ứng tuyển
            }
            return ResponseEntity.ok(applicationDTOS);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);  // Trả về mã lỗi 500 nếu có ngoại lệ
        }
    }

}
