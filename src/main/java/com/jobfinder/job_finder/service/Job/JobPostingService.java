package com.jobfinder.job_finder.service.Job;

import com.jobfinder.job_finder.dto.jobposting.request.CreateJobPostingRequest;
import com.jobfinder.job_finder.dto.jobposting.request.UpdateJobPostingRequest;
import com.jobfinder.job_finder.entity.User;
import com.jobfinder.job_finder.entity.job.JobPosting;
import com.jobfinder.job_finder.repository.JobPostingRepository;
import com.jobfinder.job_finder.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobPostingService {
    @Autowired
    private final JobPostingRepository jobPostingRepository;
    private final UserRepository userRepository;

    public JobPostingService(JobPostingRepository jobPostingRepository, UserRepository userRepository) {
        this.jobPostingRepository = jobPostingRepository;
        this.userRepository = userRepository;
    }

    // Tạo mới tin tuyển dụng
    // nhận thông tin từ request và id nhà tuyển dụng 
    // mặc mặc định trạng thái hoạt động là true và thời gian tạo lấy là lúc tạo
    // và trả về bài đăng mới
    public JobPosting createJobPosting(CreateJobPostingRequest request, Long recruiterId) {
        JobPosting jobPosting = new JobPosting(request);
        User user = userRepository.findById(recruiterId)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        jobPosting.setRecruiter(user);
        return (JobPosting) jobPostingRepository.save(jobPosting);
    }

    // Tìm kiếm tin tuyển dụng của nhà tuyển dụng
    public List<JobPosting> getJobPostings(Long recruiterId) {
        return jobPostingRepository.findByRecruiterId(recruiterId);
    }

    // Cập nhật tin tuyển dụng
    public JobPosting updateJobPosting(Long jobId, Long recruiterId, UpdateJobPostingRequest request) {
        JobPosting existingJob = jobPostingRepository
                                .findByIdAndRecruiterId(jobId, recruiterId)
                                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài đăng tuyển dụng"));
        existingJob.setTitle(request.getTitle());
        existingJob.setJobDescription(request.getJobDescription());
        existingJob.setLocation(request.getLocation());
        existingJob.setCandidateRequirements(request.getCandidateRequirements());
        existingJob.setSalary(request.getSalary());
        existingJob.setBenefits(request.getBenefits());
        existingJob.setQuantity(request.getQuantity());
        existingJob.setApplicationDeadline(request.getApplicationDeadline());

        return jobPostingRepository.save(existingJob);  // Lưu bài đăng đã cập nhật
    }

    // Hủy tin tuyển dụng
    public void deleteJobPosting(Long jobId) {
        Optional<JobPosting> existingJob = jobPostingRepository.findById(jobId);
        if (existingJob.isPresent()) {
            JobPosting jobPosting = existingJob.get();
            jobPosting.setActive(false);  // Cập nhật trạng thái thành hủy
            jobPostingRepository.save(jobPosting);
        } else {
            throw new RuntimeException("Job posting not found or does not belong to this recruiter");
        }
    }

    // Lấy tất cả các tin tuyển dụng của tất cả nhà tuyển dụng
    public List<JobPosting> getAllJobPostings() {
        return jobPostingRepository.findAll();  // Trả về tất cả các tin tuyển dụng
    }
    
    public List<JobPosting> getAllJobPostingsAndActiveTure() {
        return jobPostingRepository.findByisActiveTrue();  // Trả về tất cả các tin tuyển dụng
    }

}
